package com.employee.management;

import com.employee.management.model.Department;
import com.employee.management.model.Employee;
import com.employee.management.model.EmployeeDTO;
import com.employee.management.projection.EmployeeProjection;
import com.employee.management.service.DepartmentService;
import com.employee.management.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeManagementSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoRunner(EmployeeService employeeService, DepartmentService departmentService) {
        return args -> {
            System.out.println("\n--- RUNNING EMPLOYEE MANAGEMENT SYSTEM DEMO (EXERCISES 1-10) ---");

            // 1. Setup Departments
            Department hr = new Department();
            hr.setName("Human Resources");
            hr = departmentService.save(hr);

            Department it = new Department();
            it.setName("Information Technology");
            it = departmentService.save(it);

            System.out.println("Saved Departments: HR, IT");

            // 2. Setup Employees (Exercise 2 & 4: Create/Save)
            Employee e1 = new Employee();
            e1.setName("Alice Johnson");
            e1.setEmail("alice@company.com");
            e1.setDepartment(it);
            e1 = employeeService.save(e1);

            Employee e2 = new Employee();
            e2.setName("Bob Smith");
            e2.setEmail("bob@company.com");
            e2.setDepartment(hr);
            e2 = employeeService.save(e2);

            Employee e3 = new Employee();
            e3.setName("Charlie Brown");
            e3.setEmail("charlie@company.com");
            e3.setDepartment(it);
            e3 = employeeService.save(e3);

            System.out.println("Saved Employees: Alice, Bob, Charlie");

            // 3. Entity Auditing Verification (Exercise 7)
            System.out.println("\n============ Exercise 7: Auditing Verification ============");
            System.out.println("Employee 'Alice' Audit Info:");
            System.out.println("  Created By: " + e1.getCreatedBy());
            System.out.println("  Created Date: " + e1.getCreatedDate());
            System.out.println("  Last Modified By: " + e1.getLastModifiedBy());
            System.out.println("  Last Modified Date: " + e1.getLastModifiedDate());

            // 4. Custom Queries Verification (Exercise 5)
            System.out.println("\n============ Exercise 5: Custom Query Methods ============");
            System.out.println("Derived Query: findByNameContaining('Brown'):");
            employeeService.findByNameContaining("Brown").forEach(System.out::println);

            System.out.println("JPQL Query: findByDepartmentName('Information Technology'):");
            employeeService.findByDepartmentName("Information Technology").forEach(System.out::println);

            System.out.println("Named Query: findByNameNamedQuery('Bob Smith'):");
            employeeService.findByNameNamedQuery("Bob Smith").forEach(System.out::println);

            System.out.println("Native Query: findByEmailNative('alice@company.com'):");
            System.out.println(employeeService.findByEmailNative("alice@company.com"));

            // 5. Pagination and Sorting Verification (Exercise 6)
            System.out.println("\n============ Exercise 6: Pagination and Sorting ============");
            PageRequest pageable = PageRequest.of(0, 2, Sort.by("name").descending());
            Page<Employee> employeePage = employeeService.getAll(pageable);
            System.out.println("Page 0 details (size 2, sorted by Name DESC):");
            System.out.println("  Total Elements: " + employeePage.getTotalElements());
            System.out.println("  Total Pages: " + employeePage.getTotalPages());
            System.out.println("  Content in current page:");
            employeePage.getContent().forEach(e -> System.out.println("    - " + e.getName() + " (" + e.getEmail() + ")"));

            // 6. Projections Verification (Exercise 8)
            System.out.println("\n============ Exercise 8: Interface and Class Projections ============");
            System.out.println("Interface-based Projection (Employees in IT department):");
            List<EmployeeProjection> projectedList = employeeService.findProjectedByDepartmentId(it.getId());
            for (EmployeeProjection proj : projectedList) {
                System.out.println("  Name: " + proj.getName() + " | Email: " + proj.getEmail());
                System.out.println("  SpEL Projection (FullNameAndEmail): " + proj.getFullNameAndEmail());
            }

            System.out.println("Class-based Projection (EmployeeDTO for ID 1):");
            EmployeeDTO dto = employeeService.findDtoById(e1.getId());
            if (dto != null) {
                System.out.println("  DTO Name: " + dto.getName() + " | DTO Email: " + dto.getEmail());
            }

            // 7. Hibernate Batch Operations Verification (Exercise 10)
            System.out.println("\n============ Exercise 10: Hibernate Bulk Batch Processing ============");
            List<Employee> bulkEmployees = new ArrayList<>();
            for (int i = 1; i <= 30; i++) {
                Employee temp = new Employee();
                temp.setName("Batch Worker " + i);
                temp.setEmail("worker" + i + "@company.com");
                temp.setDepartment(it);
                bulkEmployees.add(temp);
            }
            System.out.println("Triggering batch insert of 30 employees...");
            employeeService.saveAllBatch(bulkEmployees);
            System.out.println("Batch insert finished. Total employees in database: " + employeeService.getAll().size());

            System.out.println("\n--- DEMO RUN COMPLETED SUCCESSFULLY ---");
        };
    }
}
