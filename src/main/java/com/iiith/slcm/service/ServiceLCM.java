package com.iiith.slcm.service;

import com.iiith.slcm.businessentities.ServiceSchema;
import com.iiith.slcm.dao.ServiceLCMDAO;
import com.iiith.slcm.dataentities.PendingRequests;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceLCM {

    @Autowired
    private ServiceLCMDAO serviceLCMDAO;

    public void startService(ServiceSchema serviceSchema) {
        PendingRequests pendingRequests = new PendingRequests();
        pendingRequests.setUserId(serviceSchema.getUserId());
        pendingRequests.setServiceId(serviceSchema.getServiceId());
        pendingRequests.setServiceName(serviceSchema.getServiceName());
        pendingRequests.setServerIp("Someip");
        pendingRequests.setSshPort("some port");

        serviceLCMDAO.addServiceInfo(pendingRequests);
//        serviceLCMDAO.getServiceInfo(serviceSchema.getServiceId());
    }
}
