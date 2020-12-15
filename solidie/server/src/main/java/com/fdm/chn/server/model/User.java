package com.fdm.chn.server.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    private String id;
    private String username;
    private String email;

    public void add(Diary diary) {
        diary.setUser(this);
    }

    public void remove(Diary diary) {
        diary.setUser(null);
    }
}
