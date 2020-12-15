package com.fdm.pond.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fdm.pond.model.schedule.ScheduleEntry;

@Repository
public interface ScheduleEntryRepository extends PagingAndSortingRepository<ScheduleEntry, Long> {

    Iterable<ScheduleEntry> findByConsultant_EmployeeID(Long id);
}
