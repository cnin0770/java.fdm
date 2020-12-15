package com.fdm.chn.server.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "exercises", path = "exercises")
public interface ExerciseRestRepo extends JpaRepository<Exercise, Long> {
    List<Exercise> findByTitle(@Param("title") String title);
    List<Exercise> findByDiary(@Param("diary") Diary diary);

//    usage: http://localhost:14350/api/exercises/search/findByTitle?title=pull+up
}