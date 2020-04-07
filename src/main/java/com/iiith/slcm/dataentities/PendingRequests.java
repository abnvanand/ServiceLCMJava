package com.iiith.slcm.dataentities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"PendingRequest\"")
public class PendingRequests {
    @Id
    @Column(name = "\"serviceId\"")
    private String serviceId;
    
    @Column(name = "\"serviceName\"")
    private String serviceName;
    
    @Column(name = "\"userId\"")
    private String userId;
    
    @Column(name = "\"serverIp\"")
    private String serverIp;
    
    @Column(name = "\"serverPort\"")
    private String serverPort;

    public PendingRequests() {
    }


    public PendingRequests(String serviceId, String serviceName,
                           String userId, String serverIp,
                           String serverPort) {
        super();
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.userId = userId;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String sshPort) {
        this.serverPort = sshPort;
    }
}
