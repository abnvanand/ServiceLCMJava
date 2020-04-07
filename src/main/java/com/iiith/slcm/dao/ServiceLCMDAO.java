package com.iiith.slcm.dao;

import com.iiith.slcm.dataentities.PendingRequests;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Transactional
@Repository
public class ServiceLCMDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

//    public void add(Service topic) {
//
//        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
//        session.save(topic);
//
//    }

//    public Service getById(String id) {
//        Service topic = null;
//
//        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
//        topic = session.get(Service.class, id);
//        return topic;
//    }

    public void addServiceInfo(PendingRequests pendingRequests) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.save(pendingRequests);
    }

    public PendingRequests getServiceInfo(String serviceId) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        PendingRequests pendingRequests = session.get(PendingRequests.class, serviceId);
        return pendingRequests;
    }
}
