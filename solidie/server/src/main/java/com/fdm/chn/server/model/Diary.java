package com.fdm.chn.server.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String date;
    private double weight;
    private String focus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    public void add(Exercise exercise) {
        exercise.setDiary(this);
        exercise.setUser(this.user);
    }

    public void remove(Exercise exercise) {
        exercise.setDiary(null);
        exercise.setUser(null);
    }
}
