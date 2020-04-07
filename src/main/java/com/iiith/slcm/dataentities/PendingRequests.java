package com.iiith.slcm.dataentities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Table(name = "pendingrequests")
public class PendingRequests {
    @Id
//    @Column(name = "\"serviceId\"")
    private String serviceId;
    //    @Column(name = "\"serviceName\"")
    private String serviceName;
    //    @Column(name = "\"userId\"")
    private String userId;
    //    @Column(name = "\"serverIp\"")
    private String serverIp;
    //    @Column(name = "\"sshPort\"")
    private String sshPort;

    public PendingRequests() {
    }


    public PendingRequests(String serviceId, String serviceName,
                           String userId, String serverIp,
                           String sshPort) {
        super();
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.userId = userId;
        this.serverIp = serverIp;
        this.sshPort = sshPort;
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

    public String getSshPort() {
        return sshPort;
    }

    public void setSshPort(String sshPort) {
        this.sshPort = sshPort;
    }
}
