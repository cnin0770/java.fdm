package com.fdm.w8.users;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserRawDaoTest {
    private UserRawDao dao;

    @Before
    public void initTest() {
        dao = new UserRawDao();
    }

    void createTable() {
        dao.createTable();
    }

    void addUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Leo"));
        users.add(new User("Raph"));
        users.add(new User("Don"));
        users.add(new User("Mikey"));
        dao.addUser(users);
    }

    @Test
    public void changeUser() {
        User don = dao.getUser("Don");
        User donnie = dao.getUser("Donnie");
        if (don == null && donnie != null) {
            donnie.setUsername("Don");
            dao.modUser(donnie);
        } else if (don != null && donnie == null) {
            don.setUsername("Donnie");
            dao.modUser(don);
        }
        dao.getAllUser().forEach(System.out::println);
    }

    @Test
    public void addAndDel() {
        User splinter = new User("Splinter");
        int a = dao.getAllUser().size();
        dao.addUser(splinter);
        int b = dao.getAllUser().size();
        dao.delUser(splinter.getUsername());
        int c = dao.getAllUser().size();

        assertTrue(a == c && a != b);
    }
}
