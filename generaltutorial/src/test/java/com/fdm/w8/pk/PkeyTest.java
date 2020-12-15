package com.fdm.w8.pk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PkeyTest {
    EntityManagerFactory factory;
    EntityManager manager;
    EntityTransaction transaction;

    Pkey p1 = new Pkey("b1");
    Pkey p2 = new Pkey("b2");
    Pkey p3 = new Pkey("b3");

    @Before
    public void init() {
        factory = Persistence.createEntityManagerFactory("W8");
        manager = factory.createEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
    }

    @After
    public void finish() {
//        transaction.commit();
        manager.close();
        factory.close();
    }

    @Test
    public void a() {
        manager.persist(p1);
        manager.persist(p2);
        manager.persist(p3);
    }
}
