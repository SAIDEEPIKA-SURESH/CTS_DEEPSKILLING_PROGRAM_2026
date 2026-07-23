package com.employee.management.service;

import com.employee.management.model.Department;
import com.employee.management.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Department> getById(Long id) {
        return departmentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Department> getByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Transactional
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public Department update(Long id, Department deptDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found for id: " + id));
        department.setName(deptDetails.getName());
        return departmentRepository.save(department);
    }

    @Transactional
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}
