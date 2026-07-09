package com.employee.management.repository;

import com.employee.management.model.Employee;
import com.employee.management.model.EmployeeDTO;
import com.employee.management.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Derived Query Methods (Exercise 3)
    List<Employee> findByNameContaining(String name);
    List<Employee> findByEmailEndingWith(String domain);

    // Named Query declaration (Exercise 5)
    // Spring Data Jpa matches these by name to the @NamedQuery declarations on the Employee class.
    List<Employee> findByName(@Param("name") String name);
    Employee findByEmail(@Param("email") String email);

    // Custom JPQL queries with @Query (Exercise 5)
    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName")
    List<Employee> findByDepartmentName(@Param("deptName") String deptName);

    // Custom Native Query with @Query (Exercise 5)
    @Query(value = "SELECT * FROM employees WHERE email = :email", nativeQuery = true)
    Employee findByEmailNative(@Param("email") String email);

    // Interface-based Projection query (Exercise 8)
    List<EmployeeProjection> findProjectedByDepartmentId(Long deptId);

    // Class-based Projection / DTO query (Exercise 8)
    @Query("SELECT new com.employee.management.model.EmployeeDTO(e.name, e.email) FROM Employee e WHERE e.id = :id")
    EmployeeDTO findDtoById(@Param("id") Long id);
}
