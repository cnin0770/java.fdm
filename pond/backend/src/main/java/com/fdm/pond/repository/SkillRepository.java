package com.fdm.pond.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fdm.pond.model.employee.Skill;

public interface SkillRepository extends PagingAndSortingRepository<Skill, String>{
}
