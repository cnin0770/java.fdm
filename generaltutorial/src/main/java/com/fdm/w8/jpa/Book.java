package com.fdm.w8.jpa;

import javax.persistence.*;

@Entity
@NamedQueries(
        @NamedQuery(name = "findByNamedQuery", query = "SELECT b FROM Pkey b WHERE LOWER(b.name) LIKE CONCAT('%', :bookName, '%')")
)
@Table(name = "BOOK")
public class Book {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    public Book() {
        super();
    }

    public Book(int id, String name) {
        super();
        this.id = id;
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
        return "[Book] " +
                "id=" + id +
                ", name='" + name + '\'' +
                '.';
    }

    public void update(Book book) {
        this.setName(book.getName());
    }
}
