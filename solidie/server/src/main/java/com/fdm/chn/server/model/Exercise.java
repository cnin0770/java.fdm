package com.fdm.chn.server.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    private String title;
    @NonNull
    private int repetition;
    private String category;
    private String comment;
    private double measure;
    private String measureUnit;
    private int count;
    private String countUnit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Diary diary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;
}
