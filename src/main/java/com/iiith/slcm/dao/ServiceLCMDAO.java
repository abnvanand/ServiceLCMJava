package com.iiith.slcm.dao;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iiith.slcm.dataentities.PendingRequests;

@Component
public class ServiceLCMDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void addServiceInfo(PendingRequests pendingRequests) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction tx = session.beginTransaction();
        session.save(pendingRequests);
        tx.commit();
    }

    public PendingRequests getServiceInfo(String serviceId) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        PendingRequests pendingRequests = session.get(PendingRequests.class, serviceId);
        return pendingRequests;
    }
}
