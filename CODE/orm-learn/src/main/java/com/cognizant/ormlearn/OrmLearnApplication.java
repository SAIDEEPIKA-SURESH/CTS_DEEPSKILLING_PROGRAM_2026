package com.cognizant.ormlearn;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Skill;

import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.SkillService;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService service;
    private static StockRepository stockRepository;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

        service = context.getBean(CountryService.class);
        stockRepository = context.getBean(StockRepository.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);

        System.out.println("\n============ Country Repository Tests ============");
        testGetAllCountries();
        testFindCountry();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testSearchCountry();
        testSearchCountryAsc();
        testSearchCountryStartingWith();

        System.out.println("\n============ Stock Repository Tests ============");
        testFacebookSeptember2019();
        testGoogleStockCloseGreaterThan1250();
        testTop3HighestVolumeTransactions();
        testTop3NetflixLowestClose();

        System.out.println("\n============ Payroll Relationship Tests ============");
        testGetEmployee();
        testAddEmployee();
        testUpdateEmployee();
        testGetDepartment();
        testAddSkillToEmployee();
    }

    // Country Tests
    private static void testGetAllCountries() {
        System.out.println("GET ALL:");
        List<Country> countries = service.getAllCountries();
        countries.forEach(System.out::println);
    }

    private static void testFindCountry() throws Exception {
        System.out.println("FIND COUNTRY BY CODE 'IN':");
        System.out.println(service.findCountryByCode("IN"));
    }

    private static void testAddCountry() {
        service.addCountry(new Country("SA", "Saudi Arabia"));
        System.out.println("Country Added");
    }

    private static void testUpdateCountry() throws Exception {
        service.updateCountry("SA", "Saudi");
        System.out.println("Country Updated");
    }

    private static void testDeleteCountry() {
        service.deleteCountry("SA");
        System.out.println("Country Deleted");
    }

    private static void testSearchCountry() {
        System.out.println("SEARCH COUNTRY CONTAINING 'Uni':");
        List<Country> countries = service.searchCountry("Uni");
        countries.forEach(System.out::println);
    }

    private static void testSearchCountryAsc() {
        System.out.println("SEARCH Country Containing 'ou' Sorted Ascending:");
        List<Country> countries = service.searchCountryAsc("ou");
        countries.forEach(System.out::println);
    }

    private static void testSearchCountryStartingWith() {
        System.out.println("SEARCH Country Starting With 'Z':");
        List<Country> countries = service.searchCountryStartingWith("Z");
        countries.forEach(System.out::println);
    }

    // Stock Tests
    private static void testFacebookSeptember2019() {
        System.out.println("Facebook stock details in Sept 2019:");
        List<Stock> stocks = stockRepository.findByCodeAndDateBetween("FB", LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 30));
        System.out.println("+---------+------------+---------+----------+-----------+");
        System.out.println("| st_code | st_date    | st_open | st_close | st_volume |");
        System.out.println("+---------+------------+---------+----------+-----------+");
        stocks.forEach(System.out::println);
        System.out.println("+---------+------------+---------+----------+-----------+");
    }

    private static void testGoogleStockCloseGreaterThan1250() {
        System.out.println("Google stock details close > 1250:");
        List<Stock> stocks = stockRepository.findByCodeAndCloseGreaterThan("GOOGL", BigDecimal.valueOf(1250));
        System.out.println("+---------+------------+---------+----------+-----------+");
        System.out.println("| st_code | st_date    | st_open | st_close | st_volume |");
        System.out.println("+---------+------------+---------+----------+-----------+");
        stocks.forEach(System.out::println);
        System.out.println("+---------+------------+---------+----------+-----------+");
    }

    private static void testTop3HighestVolumeTransactions() {
        System.out.println("Top 3 highest volume transactions:");
        List<Stock> stocks = stockRepository.findTop3ByOrderByVolumeDesc();
        System.out.println("+---------+------------+---------+----------+-----------+");
        System.out.println("| st_code | st_date    | st_open | st_close | st_volume |");
        System.out.println("+---------+------------+---------+----------+-----------+");
        stocks.forEach(System.out::println);
        System.out.println("+---------+------------+---------+----------+-----------+");
    }

    private static void testTop3NetflixLowestClose() {
        System.out.println("Netflix lowest close stocks (Top 3):");
        List<Stock> stocks = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        System.out.println("+---------+------------+---------+----------+-----------+");
        System.out.println("| st_code | st_date    | st_open | st_close | st_volume |");
        System.out.println("+---------+------------+---------+----------+-----------+");
        stocks.forEach(System.out::println);
        System.out.println("+---------+------------+---------+----------+-----------+");
    }

    // Payroll Relationship Tests
    private static void testGetEmployee() {
        LOGGER.info("Start testGetEmployee");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("End testGetEmployee");
    }

    private static void testAddEmployee() {
        LOGGER.info("Start testAddEmployee");
        Employee employee = new Employee();
        employee.setName("David");
        employee.setSalary(70000.00);
        employee.setPermanent(true);
        // Using static past date for birth
        employee.setDateOfBirth(java.sql.Date.valueOf("1994-08-10"));
        Department department = departmentService.get(1);
        employee.setDepartment(department);
        employeeService.save(employee);
        LOGGER.debug("Employee saved: {}", employee);
        LOGGER.info("End testAddEmployee");
    }

    private static void testUpdateEmployee() {
        LOGGER.info("Start testUpdateEmployee");
        Employee employee = employeeService.get(1);
        Department department = departmentService.get(2);
        employee.setDepartment(department);
        employeeService.save(employee);
        LOGGER.debug("Employee updated: {}", employee);
        LOGGER.info("End testUpdateEmployee");
    }

    private static void testGetDepartment() {
        LOGGER.info("Start testGetDepartment");
        Department department = departmentService.get(1);
        LOGGER.debug("Department:{}", department);
        LOGGER.debug("Employees in department:{}", department.getEmployeeList());
        LOGGER.info("End testGetDepartment");
    }

    private static void testAddSkillToEmployee() {
        LOGGER.info("Start testAddSkillToEmployee");
        Employee employee = employeeService.get(2); // Alice
        Skill skill = skillService.get(3); // SQL
        employee.getSkillList().add(skill);
        employeeService.save(employee);
        LOGGER.debug("Added Skill to Employee: {}", employee);
        LOGGER.info("End testAddSkillToEmployee");
    }
}
