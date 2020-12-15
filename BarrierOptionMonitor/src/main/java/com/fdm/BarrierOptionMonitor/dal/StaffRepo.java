package com.fdm.BarrierOptionMonitor.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.BarrierOptionMonitor.model.Staff;

public interface StaffRepo extends JpaRepository<Staff, Long> {

    Staff findByName(String name);
}
