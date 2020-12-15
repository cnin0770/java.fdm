package com.fdm.BarrierOptionMonitor;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.fdm.BarrierOptionMonitor.controller", "com.fdm.BarrierOptionMonitor.config"})
@EntityScan(basePackages = {"com.fdm.BarrierOptionMonitor.model"})
@EnableJpaRepositories(basePackages = {"com.fdm.BarrierOptionMonitor.dal"})
@EnableScheduling
@Log4j2
public class BarrierOptionMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarrierOptionMonitorApplication.class, args);
    }
}
