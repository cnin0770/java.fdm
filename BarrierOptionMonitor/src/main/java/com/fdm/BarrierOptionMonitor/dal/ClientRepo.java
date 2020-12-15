package com.fdm.BarrierOptionMonitor.dal;

import java.util.List;

import com.fdm.BarrierOptionMonitor.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
    long countByName(String name);

    List<Client> findByName(String name);
}
