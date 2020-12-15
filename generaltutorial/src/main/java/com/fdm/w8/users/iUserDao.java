package com.fdm.w8.users;

import java.util.List;

interface iUserDao {
    User getUser(String username);
    void addUser(User user);
    void addUser(List<User> users);
    void delUser(String username);
    void modUser(User user);
    List<User> getAllUser();
}
