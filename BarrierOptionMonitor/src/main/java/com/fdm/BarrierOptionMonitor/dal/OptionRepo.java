package com.fdm.BarrierOptionMonitor.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fdm.BarrierOptionMonitor.model.Option;

public interface OptionRepo extends JpaRepository<Option, Long> {
    @Transactional
    @Modifying
    @Query("delete from Option o where o.client.id =:clientID")
    void CustomRemoveOptionForClient(@Param("clientID") long id);
}
