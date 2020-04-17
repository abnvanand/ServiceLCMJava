package com.iiith.slcm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiith.slcm.businessentities.DeployRequest;
import com.iiith.slcm.businessentities.DeploymentResponse;
import com.iiith.slcm.businessentities.ServerInfo;
import com.iiith.slcm.businessentities.ServiceSchema;
import com.iiith.slcm.dao.ServiceLCMDAO;
import com.iiith.slcm.dao.TopologyDAO;
import com.iiith.slcm.dataentities.PendingRequests;
import com.iiith.slcm.dataentities.Topology;
import com.iiith.slcm.util.PlatformProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceLCM {
    PlatformProperties platformProperties = PlatformProperties.getInstance();
    @Autowired
    private ServiceLCMDAO serviceLCMDAO;
    @Autowired
    private TopologyDAO topologyDAO;

    public void startService(ServiceSchema serviceSchema) {
        PendingRequests pendingRequests = new PendingRequests();
        pendingRequests.setUsername(serviceSchema.getUsername());
        pendingRequests.setServiceId(serviceSchema.getServiceId());
        pendingRequests.setServiceName(serviceSchema.getServicename());
        pendingRequests.setApplicationName(serviceSchema.getApplicationname());

        Topology topology = topologyDAO.getTopologyInfo(serviceSchema.getServiceId());
        if (topology == null) {
            String URL_ALLOCATE_SERVER = String.format("http://%s:%s/serverlcm/allocate_server/%s",
                    platformProperties.getServerLCMIp(),
                    platformProperties.getServerLCMPort(),
                    serviceSchema.getServiceId());

            sendRequestToServerLCM(URL_ALLOCATE_SERVER);
            serviceLCMDAO.addServiceInfo(pendingRequests);
        } else {
            topology.setDependencyCount(topology.getDependencyCount() + 1);
            topologyDAO.addTopologyInfo(topology);
        }
    }

    public void stopService(ServiceSchema serviceSchema) {
        Topology topology = topologyDAO.getTopologyInfo(serviceSchema.getServiceId());

        if (topology != null) {
            if (topology.getDependencyCount() > 0) {
                topology.setDependencyCount(topology.getDependencyCount() - 1);
                topologyDAO.addTopologyInfo(topology);
            } else if (topology.getDependencyCount() == 0) {
                String url = String.format("http://%s:%s/v1.24/containers/%s/stop?t=1",
                        topology.getIp(),
                        topology.getPort(),
                        topology.getContainerId());

                boolean isStopped = sendStopContainerRequest(topology, url);
                if (isStopped) {
                    topology.setStatus("stopped");
                } else {
                    topology.setStatus("failedToStop");
                }
                topologyDAO.addTopologyInfo(topology);
            }
        }
    }

    public void updateServiceWithIpPort(ServerInfo serverInfo) {
        PendingRequests pendingRequests = serviceLCMDAO.getServiceInfo(serverInfo.getServiceid());
        pendingRequests.setServerIp(serverInfo.getServerip());
        pendingRequests.setSshPort(serverInfo.getSshPort());
        pendingRequests.setSshPassword(serverInfo.getPassword());
        pendingRequests.setSshUsername(serverInfo.getMachineusername());

        serviceLCMDAO.updateServiceInfo(pendingRequests);

        // TODO: call DeploymentManager
        DeployRequest deployRequest = new DeployRequest(
                pendingRequests.getUsername(),
                pendingRequests.getApplicationName(),
                pendingRequests.getServiceName(),
                pendingRequests.getServiceId(),
                pendingRequests.getServerIp(),
                pendingRequests.getSshPort(),
                pendingRequests.getSshUsername(),
                pendingRequests.getSshPassword());

        String URL_DO_DEPLOY = String.format("http://%s:%s/deployment/dodeploy",
                platformProperties.getDeploymentManagerIp(),
                platformProperties.getDeploymentManagerPort()
        );

        sendStartRequestToDeploymentManager(deployRequest, URL_DO_DEPLOY);
    }

    public void updateTopology(DeploymentResponse deploymentResponse) {
        Topology topology = new Topology();
        topology.setServiceId(deploymentResponse.getServiceId());
        topology.setServiceName(deploymentResponse.getServiceName());
        topology.setUsername(deploymentResponse.getUsername());
        topology.setContainerId(deploymentResponse.getContainerId());
        topology.setIp(deploymentResponse.getIp());
        topology.setPort(deploymentResponse.getPort());
        topology.setStatus("alive");
        topology.setRedeployRequest("false");
        topology.setApplicationName(deploymentResponse.getApplicationName());

        topologyDAO.addTopologyInfo(topology);
    }

    public void deleteServiceInfo(DeploymentResponse deploymentResponse) {
        serviceLCMDAO.deleteServiceInfo(deploymentResponse.getServiceId());
    }

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private void sendRequestToServerLCM(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        System.out.println("Sending request to: " + url);

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println("response.statusCode(): " + response.statusCode());
                    return response;
                })
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
    }

    private void sendStartRequestToDeploymentManager(DeployRequest deployRequest, String url) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = "";
        try {
            jsonBody = objectMapper.writeValueAsString(deployRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        System.out.println("jsonBody: " + jsonBody);
        System.out.println("url: " + url);

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println(response.statusCode());
                    return response;
                })
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);

    }

    private boolean sendStopContainerRequest(Topology topology, String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(null)
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return false;
        }

        if (response.statusCode() == 500)
            return false;

        return true;
    }

    public void redeployService(ServiceSchema serviceSchema) {
        PendingRequests pendingRequests = new PendingRequests();
        pendingRequests.setUsername(serviceSchema.getUsername());
        pendingRequests.setServiceId(serviceSchema.getServiceId());
        pendingRequests.setServiceName(serviceSchema.getServicename());
        pendingRequests.setApplicationName(serviceSchema.getApplicationname());

        String URL_ALLOCATE_SERVER = String.format("http://%s:%s/serverlcm/allocate_server/%s",
                platformProperties.getServerLCMIp(),
                platformProperties.getServerLCMPort(),
                serviceSchema.getServiceId());

        sendRequestToServerLCM(URL_ALLOCATE_SERVER);
        serviceLCMDAO.addServiceInfo(pendingRequests);
    }

    public List<Topology> getTopologyForUser(String userId) {
        return topologyDAO.getTopologyForUser(userId);
    }
}
