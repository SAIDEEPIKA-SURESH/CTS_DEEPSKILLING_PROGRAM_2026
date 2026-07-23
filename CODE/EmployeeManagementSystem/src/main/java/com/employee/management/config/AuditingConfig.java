package com.employee.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider") // Exercise 7
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // Return a dummy auditor (in real systems, integrated with Spring Security context)
        return () -> Optional.of("system_admin");
    }
}
