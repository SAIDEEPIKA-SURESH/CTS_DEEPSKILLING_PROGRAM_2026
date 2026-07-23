package com.cognizant.hqllearn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.cognizant.hqllearn.model.Employee;
import com.cognizant.hqllearn.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee get(int id) {
        LOGGER.info("Start");
        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Employee employee) {
        LOGGER.info("Start");
        employeeRepository.save(employee);
        LOGGER.info("End");
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllPermanentEmployees() {
        return employeeRepository.getAllPermanentEmployees();
    }

    @Transactional(readOnly = true)
    public double getAverageSalary(int id) {
        return employeeRepository.getAverageSalary(id);
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployeesNative() {
        return employeeRepository.getAllEmployeesNative();
    }
}
