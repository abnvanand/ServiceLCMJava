package com.iiith.slcm.businessentities;

public class DeploymentResponse {
    private String serviceId;
    private String serviceName;
    private String username;
    private String status; // success or failure
    private String ip;
    // Admin service => forwarded port
    // User service => Docker daemon port
    private String port;
    private String containerId;
    private String applicationName;

    @Override
    public String toString() {
        return "DeploymentResponse{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", username='" + username + '\'' +
                ", status='" + status + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", containerId='" + containerId + '\'' +
                ", applicationName='" + applicationName + '\'' +
                '}';
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public DeploymentResponse() {
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getContainerId() {
        return containerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
}
