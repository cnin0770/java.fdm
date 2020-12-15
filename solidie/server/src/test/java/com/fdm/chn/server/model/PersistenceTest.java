package com.fdm.chn.server.model;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@Log4j2
public class PersistenceTest implements CommandLineRunner {
    private final UserRepo users;
    private final DiaryRepo diaries;
    private final ExerciseRepo exercises;

    @Autowired
    public PersistenceTest(UserRepo users, DiaryRepo diaries, ExerciseRepo exercises) {
        this.users = users;
        this.diaries = diaries;
        this.exercises = exercises;
    }

    @Override
    public void run(String... str) {
        User user = User.builder()
                .id("cnin")
                .username("cnin")
                .email("c@n.com")
                .build();

        users.save(user);

        Diary diary = Diary.builder()
                .user(user)
                .date("today")
                .focus("upper")
                .build();

        diaries.save(diary);

        exercises.save(Exercise.builder()
                .title("pull up")
                .repetition(4)
                .category("upper")
                .diary(diary)
                .build());

        exercises.save(Exercise.builder()
                .title("push up")
                .repetition(4)
                .category("upper")
                .diary(diary)
                .build());

        exercises.findAll().forEach(log::trace);
        log.info("find by date: {}", diaries.findByDate("today"));
    }
}
