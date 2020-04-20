package com.iiith.slcm.businessentities;

public class ServiceSchema {

    private String serviceId;
    private String servicename;
    private String applicationname;
    private String username;

    @Override
    public String toString() {
        return "ServiceSchema{" +
                "serviceId='" + serviceId + '\'' +
                ", servicename='" + servicename + '\'' +
                ", applicationname='" + applicationname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public ServiceSchema() {
    }

    public String getApplicationname() {
        return applicationname;
    }

    public void setApplicationname(String applicationname) {
        this.applicationname = applicationname;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
