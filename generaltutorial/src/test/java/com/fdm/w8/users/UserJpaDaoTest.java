package com.fdm.w8.users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserJpaDaoTest {
    private EntityManagerFactory factory;
    private UserJpaDao dao;

    private User
            u1 = new User("Leo"),
            u2 = new User("Raph"),
            u3 = new User("Don"),
            u4 = new User("Mikey");

    @Before
    public void init() {
        factory = Persistence.createEntityManagerFactory("W8");
        dao = new UserJpaDao(factory);
    }

    @After
    public void finish() {
        dao.getAllUser().forEach(u -> dao.delUser(u.getUsername()));
    }

    @Test
    public void add_users() {
        dao.addUser(u1);
        assertEquals(1, dao.getAllUser().size());
    }

    @Test
    public void update_user() {
        User temp = new User(u3.getId(), "Donnie");
        dao.addUser(u3);

        dao.modUser(temp);

        assertNotNull(dao.getUser("Donnie"));
    }

    @Test
    public void add_a_list() {
        List<User> list = new ArrayList<>(Arrays.asList(u1, u2, u3, u4));
        dao.addUser(list);
        assertEquals(4, dao.getAllUser().size());
    }
}
