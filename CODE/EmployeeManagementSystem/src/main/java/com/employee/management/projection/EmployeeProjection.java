package com.employee.management.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {

    // Open/Closed Projection: fetch name
    String getName();

    // fetch email
    String getEmail();

    // SpEL projection expression (Exercise 8)
    @Value("#{target.name + ' (' + target.email + ')'}")
    String getFullNameAndEmail();
}
