package com.cognizant.hqllearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.hqllearn.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
