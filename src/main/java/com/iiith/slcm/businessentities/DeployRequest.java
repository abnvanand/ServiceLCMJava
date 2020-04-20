package com.iiith.slcm.businessentities;

public class DeployRequest {
    private String username;
    private String applicationname;
    private String servicename;
    private String serviceid;
    private String serverip;
    private String sshPort;
    private String machineusername;
    private String password;

    public DeployRequest() {
    }

    @Override
    public String toString() {
        return "DeployRequest{" +
                "username='" + username + '\'' +
                ", applicationname='" + applicationname + '\'' +
                ", servicename='" + servicename + '\'' +
                ", serviceid='" + serviceid + '\'' +
                ", serverip='" + serverip + '\'' +
                ", sshPort='" + sshPort + '\'' +
                ", machineusername='" + machineusername + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public DeployRequest(String username, String applicationname, String servicename, String serviceid, String serverip, String sshPort, String machineusername, String password) {
        this.username = username;
        this.applicationname = applicationname;
        this.servicename = servicename;
        this.serviceid = serviceid;
        this.serverip = serverip;
        this.sshPort = sshPort;
        this.machineusername = machineusername;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplicationname() {
        return applicationname;
    }

    public void setApplicationname(String applicationname) {
        this.applicationname = applicationname;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    public String getSshPort() {
        return sshPort;
    }

    public void setSshPort(String sshPort) {
        this.sshPort = sshPort;
    }

    public String getMachineusername() {
        return machineusername;
    }

    public void setMachineusername(String machineusername) {
        this.machineusername = machineusername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
