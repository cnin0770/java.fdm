package com.fdm.w8.pk;

import javax.persistence.*;

@Entity
public class Pkey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    public Pkey() {
    }

    public Pkey(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[pk] " +
                "id=" + id +
                ", name='" + name + '\'' +
                '.';
    }

    public void update(Pkey PKEY) {
        this.setName(PKEY.getName());
    }
}
