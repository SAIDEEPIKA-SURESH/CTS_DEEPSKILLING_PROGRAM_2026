package com.employee.management.controller;

import com.employee.management.model.Employee;
import com.employee.management.model.EmployeeDTO;
import com.employee.management.projection.EmployeeProjection;
import com.employee.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Exercise 4: Basic Read All
    @GetMapping("/all")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    // Exercise 6: Pagination and Sorting
    // e.g. GET /api/employees?page=0&size=5&sort=name,desc
    @GetMapping
    public Page<Employee> getAllPaginated(Pageable pageable) {
        return employeeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return employeeService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee empDetails) {
        try {
            return ResponseEntity.ok(employeeService.update(id, empDetails));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    // Custom Query Endpoints (Exercise 5)
    @GetMapping("/search/name")
    public List<Employee> searchByName(@RequestParam String name) {
        return employeeService.findByNameContaining(name);
    }

    @GetMapping("/search/domain")
    public List<Employee> searchByDomain(@RequestParam String domain) {
        return employeeService.findByEmailDomain(domain);
    }

    @GetMapping("/search/department")
    public List<Employee> searchByDepartment(@RequestParam String deptName) {
        return employeeService.findByDepartmentName(deptName);
    }

    @GetMapping("/search/named-query")
    public List<Employee> searchByNamedQuery(@RequestParam String name) {
        return employeeService.findByNameNamedQuery(name);
    }

    @GetMapping("/search/email-native")
    public ResponseEntity<Employee> searchByEmailNative(@RequestParam String email) {
        Employee emp = employeeService.findByEmailNative(email);
        return emp != null ? ResponseEntity.ok(emp) : ResponseEntity.notFound().build();
    }

    // Projection Endpoints (Exercise 8)
    @GetMapping("/projected/department/{deptId}")
    public List<EmployeeProjection> getProjectedEmployees(@PathVariable Long deptId) {
        return employeeService.findProjectedByDepartmentId(deptId);
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<EmployeeDTO> getDtoEmployee(@PathVariable Long id) {
        EmployeeDTO dto = employeeService.findDtoById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // Exercise 10: Batch Processing Endpoint
    @PostMapping("/batch")
    public ResponseEntity<String> createBatch(@RequestBody List<Employee> employees) {
        employeeService.saveAllBatch(employees);
        return ResponseEntity.ok("Batch processing completed successfully for " + employees.size() + " employees.");
    }
}
