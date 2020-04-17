package com.iiith.slcm.businessentities;

public class ServerInfo {
    String result;
    String serviceid;
    String serverip;
    String sshPort;
    String machineusername;
    String password;


    public ServerInfo() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
