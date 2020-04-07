package com.iiith.slcm.service;

import com.iiith.slcm.businessentities.DeploymentResponse;
import com.iiith.slcm.businessentities.ServerInfo;
import com.iiith.slcm.businessentities.ServiceSchema;
import com.iiith.slcm.dao.ServiceLCMDAO;
import com.iiith.slcm.dao.TopologyDAO;
import com.iiith.slcm.dataentities.PendingRequests;
import com.iiith.slcm.dataentities.Topology;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceLCM {

    @Autowired
    private ServiceLCMDAO serviceLCMDAO;
    @Autowired
    private TopologyDAO topologyDAO;

    public void startService(ServiceSchema serviceSchema) {
        PendingRequests pendingRequests = new PendingRequests();
        pendingRequests.setUserId(serviceSchema.getUserId());
        pendingRequests.setServiceId(serviceSchema.getServiceId());
        pendingRequests.setServiceName(serviceSchema.getServiceName());
        pendingRequests.setServerIp("127.0.0.1");
        pendingRequests.setSshPort("22");

        serviceLCMDAO.addServiceInfo(pendingRequests);
//        serviceLCMDAO.getServiceInfo(serviceSchema.getServiceId());
    }

    public void updateServiceWithIpPort(ServerInfo serverInfo) {
        PendingRequests pendingRequests = serviceLCMDAO.getServiceInfo(serverInfo.getServiceId());
        pendingRequests.setServerIp(serverInfo.getServerIp());
        pendingRequests.setSshPort(serverInfo.getSshPort());
        serviceLCMDAO.updateServiceInfo(pendingRequests);

        // TODO: call DeploymentManager
    }

    public void deleteServiceInfo(DeploymentResponse deploymentResponse) {
        Topology topology = new Topology();
        topology.setServiceId(deploymentResponse.getServiceId());
        topology.setUserId(deploymentResponse.getUserId());
        topology.setContainerId(deploymentResponse.getContainerId());
        topology.setIp(deploymentResponse.getIp());
        topology.setPort(deploymentResponse.getPort());
        topology.setStatus("alive");// FIXME
        topology.setRedeployRequest("false");//FIXME
        topologyDAO.addTopologyInfo(topology);
        serviceLCMDAO.deleteServiceInfo(deploymentResponse.getServiceId());
    }
}
