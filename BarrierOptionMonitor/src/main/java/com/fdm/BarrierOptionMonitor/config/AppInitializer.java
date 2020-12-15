package com.fdm.BarrierOptionMonitor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;

@Configuration
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        servletContext.setInitParameter("spring.profiles.active", "dev");
    }
}
