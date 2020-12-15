package com.fdm.w8.relation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class RelationTest {
    EntityManagerFactory factory;
    EntityManager manager;
    EntityTransaction transaction;
    OneOwner oneOwner = new OneOwner(1, "one owner");
    OneOwnee oneOwnee = new OneOwnee(2, "one ownee");
    ManyOwner manyOwner = new ManyOwner(3, "many owner");
    ManyOwnee manyOwnee = new ManyOwnee(4, "many ownee");

    @Before
    public void init() {
        factory = Persistence.createEntityManagerFactory("W8");
        manager = factory.createEntityManager();
//        Session session = manager.unwrap(Session.class);

        transaction = manager.getTransaction();
        transaction.begin();
    }

    @After
    public void finish() {
        // because this stupid hibernate session thing and create-drop bug, dont commit
//        transaction.commit();
        manager.close();
        factory.close();
    }

    @Test
    public void one_to_one() {
        oneOwner.setOneOwnee(oneOwnee);
        manager.persist(oneOwner);
    }

    @Test
    public void many_to_one() {
        manyOwnee.setOneOwner(oneOwner);
        manager.persist(manyOwnee);
    }

    @Test
    public void many_to_many() {
        manyOwner.add(manyOwnee);
        manager.persist(manyOwner);
    }

    @Test
    public void all() {
        oneOwner.setOneOwnee(oneOwnee);
        oneOwner.add(manyOwnee);
        manyOwner.setOneOwnee(oneOwnee);
        manyOwner.add(manyOwnee);
        manager.persist(oneOwner);
        manager.persist(manyOwner);
    }
}
