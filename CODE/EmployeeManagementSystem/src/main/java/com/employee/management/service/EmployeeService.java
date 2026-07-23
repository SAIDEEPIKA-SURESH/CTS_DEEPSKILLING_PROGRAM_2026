package com.employee.management.service;

import com.employee.management.model.Employee;
import com.employee.management.model.EmployeeDTO;
import com.employee.management.projection.EmployeeProjection;
import com.employee.management.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager; // Exercise 10: Batch Operations

    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // Exercise 6: Pagination and Sorting
    @Transactional(readOnly = true)
    public Page<Employee> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Employee> getById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(Long id, Employee empDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for id: " + id));
        employee.setName(empDetails.getName());
        employee.setEmail(empDetails.getEmail());
        if (empDetails.getDepartment() != null) {
            employee.setDepartment(empDetails.getDepartment());
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    // Custom Queries (Exercise 5)
    @Transactional(readOnly = true)
    public List<Employee> findByNameContaining(String name) {
        return employeeRepository.findByNameContaining(name);
    }

    @Transactional(readOnly = true)
    public List<Employee> findByEmailDomain(String domain) {
        return employeeRepository.findByEmailEndingWith(domain);
    }

    @Transactional(readOnly = true)
    public List<Employee> findByDepartmentName(String deptName) {
        return employeeRepository.findByDepartmentName(deptName);
    }

    @Transactional(readOnly = true)
    public List<Employee> findByNameNamedQuery(String name) {
        return employeeRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Employee findByEmailNative(String email) {
        return employeeRepository.findByEmailNative(email);
    }

    // Projections (Exercise 8)
    @Transactional(readOnly = true)
    public List<EmployeeProjection> findProjectedByDepartmentId(Long deptId) {
        return employeeRepository.findProjectedByDepartmentId(deptId);
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findDtoById(Long id) {
        return employeeRepository.findDtoById(id);
    }

    // Exercise 10: Batch Operations
    @Transactional
    public void saveAllBatch(List<Employee> employees) {
        int batchSize = 20;
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
                // Merge/attach department to this persistence context
                employee.setDepartment(entityManager.merge(employee.getDepartment()));
            }
            entityManager.persist(employee);
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
}
