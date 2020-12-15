package com.fdm.BarrierOptionMonitor.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdm.BarrierOptionMonitor.model.Notifications;

public interface NotificationRepo extends JpaRepository<Notifications, Long> {
    @Query("select n from Notifications n where n.staff.name =:name")
    Notifications CustomFindNotificationByName(@Param("name") String name);
}
