package com.fdm.w8.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "APPUSER")
class User{
    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();
    @Column(name = "username", length = 254, nullable = false, unique = true)
    private String username;

    public User() {
        super();
    }

    public User(String username) {
        super();
        this.username = username;
    }

    public User(String id, String username) {
        super();
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format("[USER] %s (%s).", username, id);
    }
}
