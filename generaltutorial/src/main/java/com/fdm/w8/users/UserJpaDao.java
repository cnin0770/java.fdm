package com.fdm.w8.users;

import javax.persistence.*;
import java.util.List;

public class UserJpaDao implements iUserDao {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private EntityTransaction transaction;

    public UserJpaDao(EntityManagerFactory entityManagerFactory) {
        super();
        this.factory = entityManagerFactory;
    }

    @Override
    public User getUser(String username) {
        manager = factory.createEntityManager();
        TypedQuery<User> q = manager.createQuery("select u from User u where u.username like :userName", User.class);

        q.setParameter("userName", username);
        List<User> result = q.getResultList();

        return (!result.isEmpty()) ? result.get(0) : null;
    }

    @Override
    public void addUser(User user) {
        manager = factory.createEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();

        manager.persist(user);

        transaction.commit();
        manager.close();
    }

    @Override
    public void addUser(List<User> users) {
        manager = factory.createEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();

        users.forEach(manager::persist);

        transaction.commit();
        manager.close();
    }

    @Override
    public void delUser(String username) {
        User user = getUser(username);
        if (user == null) return;

        manager = factory.createEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();

        // because should attach
        manager.remove(manager.find(User.class, user.getId()));

        transaction.commit();
        manager.close();
    }

    @Override
    public void modUser(User user) {
        manager = factory.createEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();

        // attached
        manager.find(User.class, user.getId()).setUsername(user.getUsername());

        transaction.commit();
        // detached

        manager.close();
    }

    @Override
    public List<User> getAllUser() {
        List<User> users;
        manager = factory.createEntityManager();
        TypedQuery<User> q = manager.createQuery("select u from User u", User.class);
        users = q.getResultList();
        manager.close();

        return users;
    }
}
