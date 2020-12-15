package com.fdm.chn.server.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepo extends JpaRepository<Diary, Long> {
    List<Diary> findAllByUserId(String id);
    Diary findByDate(String date);
}
