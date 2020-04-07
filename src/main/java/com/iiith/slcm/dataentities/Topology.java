package com.iiith.slcm.dataentities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"topology\"")
public class Topology {
    @Id
    @Column(name = "\"serviceId\"")
    private String serviceId;

    @Column(name = "\"userId\"")
    private String userId;

    @Column(name = "\"status\"")
    private String status;

    @Column(name = "\"ip\"")
    private String ip;

    @Column(name = "\"port\"")
    private String port;
    @Column(name = "\"containerId\"")
    private String containerId;

    @Column(name = "\"redeployRequest\"")
    private String redeployRequest;

    public Topology() {
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getRedeployRequest() {
        return redeployRequest;
    }

    public void setRedeployRequest(String redeployRequest) {
        this.redeployRequest = redeployRequest;
    }
}


