package com.iiith.slcm.dao;

import com.iiith.slcm.dataentities.Topology;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class TopologyDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void addTopologyInfo(Topology topology) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(topology);
        tx.commit();
    }
}
