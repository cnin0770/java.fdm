package com.fdm.w8.jpa;

import javax.persistence.*;
import java.util.List;

public class BookDao {
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    public BookDao(EntityManagerFactory emf) {
        super();
        this.emf = emf;
    }

    public void persist(Book book) {
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
        {
            em.persist(book);
        }
        et.commit();
        em.close();
    }

    public Book findById(int id) {
        Book found;
        em = emf.createEntityManager();
        found = em.find(Book.class, id);
        em.close();
        return found;
    }

    public void delete(Book book) {
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
        {
            Book removed = em.find(Book.class, book.getId());
            em.remove(removed);
        }
        et.commit();
        em.close();
    }

    public void update(Book book) {
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
        {
            Book updated = em.find(Book.class, book.getId());
            updated.update(book);
        }

        et.commit();
        em.close();
    }

    public Book merge(Book book) {
        Book merged = null;
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
        {
            merged = em.merge(book);
        }
        et.commit();
        em.close();
        return merged;
    }

    public List<Book> findAll() {
        List<Book> books;
        em = emf.createEntityManager();
        // use Book, class name, instead of W8BOOK, table name.
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        books = query.getResultList();
        em.close();
        return books;
    }

    public List<Book> findByName(String name) {
        List<Book> books;
        em = emf.createEntityManager();
        TypedQuery<Book> q = em.createQuery("SELECT b FROM Book b WHERE LOWER(b.name) LIKE CONCAT('%', :bookName, '%')", Book.class);
        q.setParameter("bookName", name.toLowerCase());
        books = q.getResultList();
        em.close();
        return books;
    }

    public List<Book> findByNamedQuery(String name) {
        List<Book> books;
        em = emf.createEntityManager();
        TypedQuery<Book> q = em.createNamedQuery("findByNamedQuery", Book.class);
        q.setParameter("bookName", name.toLowerCase());
        books = q.getResultList();
        em.close();
        return books;
    }
}
