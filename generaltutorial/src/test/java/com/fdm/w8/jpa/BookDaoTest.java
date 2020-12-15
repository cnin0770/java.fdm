package com.fdm.w8.jpa;

import org.junit.*;

import javax.persistence.*;

import static org.junit.Assert.*;

public class BookDaoTest {
    private EntityManagerFactory emf;
    private BookDao dao;
    private Book b1 = new Book(1, "The Adventures of Tintin");
    private Book b2 = new Book(2, "the adventures of percival the proud");

    @Before
    public void init() {
        emf = Persistence.createEntityManagerFactory("W8");
        dao = new BookDao(emf);
    }

    @After
    public void finish() {
        dao.findAll().forEach(dao::delete);
    }

    @Test
    public void persist_and_del() {
        dao.persist(b1);
        Book found = dao.findById(1);
        assertEquals(found.getName(), b1.getName());
    }

    @Test
    public void find_and_update() {
        Book temp = new Book(1, "b book");
        dao.persist(b1);
        dao.update(temp);
        Book found = dao.findById(1);
        assertEquals(found.getName(), temp.getName());
    }

    @Test
    public void merge_should_be_diff() {
        Book merged = dao.merge(b2);
        assertNotEquals(merged, b2);
    }

    @Test
    public void find_all_works() {
        dao.persist(b1);
        dao.persist(b2);
        assertEquals(2, dao.findAll().size());
    }

    @Test
    public void find_by_name() {
        dao.persist(b1);
        dao.persist(b2);
        assertEquals(2, dao.findByName("Adventure").size());
    }

    @Test
    public void find_by_named_queries() {
        dao.persist(b1);
        dao.persist(b2);
        int a = dao.findByName("Adventure").size();
        int b = dao.findByNamedQuery("Adventure").size();
        assertNotEquals(0, a);
        assertEquals(a, b);
    }
}
