package com.fdm.w8.users;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserJpaDaoMockTest {
    @Mock
    EntityManagerFactory mockFactory;
    @Mock
    EntityManager mockManager;
    @Mock
    EntityTransaction mockTransaction;
    @Mock
    TypedQuery<User> mockQuery;
    @Mock
    User mockUser, mockUserFound;

    UserJpaDao dao;
    List<User> lists = new ArrayList<>();

    @Before
    public void initMock() {
        MockitoAnnotations.initMocks(this);
        dao = new UserJpaDao(mockFactory);
        lists.add(mockUserFound);
        mockUser.setId(anyString());
        mockUser.setUsername(anyString());

        when(mockFactory.createEntityManager()).thenReturn(mockManager);
        when(mockManager.getTransaction()).thenReturn(mockTransaction);
        when(mockManager.find(User.class, mockUser.getId())).thenReturn(mockUserFound);
        when(mockManager.createQuery("select u from User u where u.username like :userName", User.class)).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(lists);
    }

    @Test
    public void assume_valid() {
        assertNull(mockUser.getId());
        assertNull(mockUser.getUsername());
        assertNotNull(mockUser);
        assertNotEquals(mockUser, mockUserFound);
    }

    @Test
    public void that_add_user_to_db() {
        dao.addUser(mockUser);

        InOrder order = inOrder(mockFactory, mockManager, mockTransaction);
        order.verify(mockFactory, times(1)).createEntityManager();
        order.verify(mockManager, times(1)).getTransaction();
        order.verify(mockTransaction, times(1)).begin();

        order.verify(mockManager, times(1)).persist(mockUser);

        order.verify(mockTransaction, times(1)).commit();
        order.verify(mockManager, times(1)).close();
    }

    @Test
    public void that_find_user_from_db() {
        User aUser = dao.getUser(mockUser.getUsername());

        InOrder order = inOrder(mockFactory, mockManager, mockQuery);
        order.verify(mockFactory, times(1)).createEntityManager();
        order.verify(mockManager, times(1)).createQuery("select u from User u where u.username like :userName", User.class);
        order.verify(mockQuery, times(1)).setParameter("userName", mockUser.getUsername());
        order.verify(mockQuery, times(1)).getResultList();
        assertEquals(mockUserFound, aUser);
    }

    @Test
    public void that_del_user_from_db() {
        dao.delUser(mockUser.getUsername());

        InOrder order = inOrder(mockFactory, mockManager, mockTransaction);
        order.verify(mockFactory, times(1)).createEntityManager();
        order.verify(mockManager, times(1)).getTransaction();
        order.verify(mockTransaction, times(1)).begin();

        // this segment: order not exchangeable
        order.verify(mockManager, times(1)).find(User.class, mockUser.getId());
        order.verify(mockManager, times(1)).remove(mockUserFound);

        order.verify(mockTransaction, times(1)).commit();
        order.verify(mockManager, times(1)).close();
    }
}
