package com.fdm.chn.server.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepo extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByUserId(String id);
}
