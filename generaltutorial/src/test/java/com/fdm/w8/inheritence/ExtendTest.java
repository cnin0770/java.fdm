package com.fdm.w8.inheritence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExtendTest {
    EntityManagerFactory factory;
    EntityManager manager;
    EntityTransaction transaction;

    ExtendMappedA extendMappedA = new ExtendMappedA(1, "upper", 1);
    ExtendMappedB extendMappedB = new ExtendMappedB(1, "upper", "lower2");

    ExtendToOneA extendToOneA = new ExtendToOneA(1, "inherit1");
    ExtendToOneB extendToOneB = new ExtendToOneB(2, "inherit2");
    ExtendToOne extendToOne = new ExtendToOne(0);

    ExtendToMany extendToMany = new ExtendToMany(1);
    ExtendToManyA extendToManyA = new ExtendToManyA(2, .2);
    ExtendToManyB extendToManyB = new ExtendToManyB(3, .3);

    ExtendJoinSub extendJoinSub = new ExtendJoinSub(1);
    ExtendJoinSubA extendJoinSubA = new ExtendJoinSubA(2, "str a");
    ExtendJoinSubB extendJoinSubB = new ExtendJoinSubB(3, "str b");

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
    public void mapped_table() {
        manager.persist(extendMappedA);
        manager.persist(extendMappedB);
    }

    @Test
    public void to_one_table() {
        manager.persist(extendToOneA);
        manager.persist(extendToOneB);
        manager.persist(extendToOne);
    }

    @Test
    public void to_many_table() {
        manager.persist(extendToMany);
        manager.persist(extendToManyA);
        manager.persist(extendToManyB);
    }

    @Test
    public void join_table() {
        manager.persist(extendJoinSub);
        manager.persist(extendJoinSubA);
        manager.persist(extendJoinSubB);
    }
}
