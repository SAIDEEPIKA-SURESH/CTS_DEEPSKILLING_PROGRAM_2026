package com.cognizant.hqllearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.cognizant.hqllearn.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value="SELECT e FROM Employee e left join fetch e.department d left join fetch e.skillList WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();

    @Query(value="SELECT AVG(e.salary) FROM Employee e where e.department.id = :id")
    double getAverageSalary(@Param("id") int id);

    @Query(value="SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}
