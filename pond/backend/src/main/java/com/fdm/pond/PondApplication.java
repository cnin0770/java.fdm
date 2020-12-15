package com.fdm.pond;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.fdm.pond.config", "com.fdm.pond.setup","com.fdm.pond.controller", "com.fdm.pond.security"})
@EnableJpaRepositories(basePackages = { "com.fdm.pond.repository" })
@EntityScan(basePackages = {"com.fdm.pond.model"})

public class PondApplication {
	@Value("${react.origin:http://localhost:3000}")
	private String reactOrigin;
	public static void main(String[] args) {
		SpringApplication.run(PondApplication.class, args);

	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins(reactOrigin);
			}
			
		};
	}

}
