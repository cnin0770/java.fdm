package com.fdm.w8.jpa;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BookDaoMockTest {
    @Mock
    EntityManagerFactory mockFactory;

    @Mock
    EntityManager mockManager;

    @Mock
    EntityTransaction mockTransaction;

    @Mock
    Book mockBook, mockFound;

    BookDao dao;

    @Before
    public void initMock() {
        MockitoAnnotations.initMocks(this);
        dao = new BookDao(mockFactory);
        when(mockFactory.createEntityManager()).thenReturn(mockManager);
        when(mockManager.getTransaction()).thenReturn(mockTransaction);
        when(mockManager.find(Book.class, mockBook.getId())).thenReturn(mockFound);
    }

    @Test
    public void assume_valid() {
        assertEquals(0, mockBook.getId());
        assertNull(mockBook.getName());
        assertNotNull(mockBook);
        assertNotEquals(mockFound, mockBook);
    }

    @Test
    public void that_persist_add_book_to_db() {
        dao.persist(mockBook);

        InOrder order = inOrder(mockFactory, mockManager, mockTransaction);
        order.verify(mockFactory, times(1)).createEntityManager();
        order.verify(mockManager, times(1)).getTransaction();
        order.verify(mockTransaction, times(1)).begin();
        order.verify(mockManager, times(1)).persist(mockBook);
        order.verify(mockTransaction, times(1)).commit();
        order.verify(mockManager, times(1)).close();
    }

    @Test
    public void that_find_by_id() {
        Book found = dao.findById(mockBook.getId());

        InOrder order = inOrder(mockFactory, mockManager);
        order.verify(mockFactory, times(1)).createEntityManager();
        order.verify(mockManager, times(1)).find(Book.class, mockBook.getId());
        order.verify(mockManager, times(1)).close();
        assertNotNull(found);
        assertEquals(found, mockFound);
    }

    @Test
    public void delete_book() {
        dao.delete(mockBook);

        InOrder order = inOrder(mockFactory, mockManager, mockTransaction);
        order.verify(mockFactory, times(1)).createEntityManager();
        order.verify(mockManager, times(1)).getTransaction();
        order.verify(mockTransaction, times(1)).begin();
        order.verify(mockManager, times(1)).find(Book.class, mockBook.getId());
        order.verify(mockManager, times(1)).remove(mockFound);
        order.verify(mockTransaction, times(1)).commit();
        order.verify(mockManager,times(1)).close();
    }

    @Test
    public void update_book() {
        dao.update(mockBook);

        InOrder order = inOrder(mockFactory, mockManager, mockTransaction, mockFound);
        order.verify(mockFactory, times(1)).createEntityManager();
        order.verify(mockManager, times(1)).getTransaction();
        order.verify(mockTransaction, times(1)).begin();
        order.verify(mockManager, times(1)).find(Book.class, mockBook.getId());
        order.verify(mockFound, times(1)).update(mockBook);
        order.verify(mockTransaction, times(1)).commit();
        order.verify(mockManager,times(1)).close();
    }
}
