package com.cognizant.hqllearn;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.hqllearn.model.Employee;
import com.cognizant.hqllearn.model.Department;
import com.cognizant.hqllearn.model.Skill;
import com.cognizant.hqllearn.model.Attempt;
import com.cognizant.hqllearn.model.AttemptQuestion;
import com.cognizant.hqllearn.model.AttemptOption;
import com.cognizant.hqllearn.model.Question;
import com.cognizant.hqllearn.model.Options;

import com.cognizant.hqllearn.service.EmployeeService;
import com.cognizant.hqllearn.service.DepartmentService;
import com.cognizant.hqllearn.service.SkillService;
import com.cognizant.hqllearn.service.AttemptService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@SpringBootApplication
public class HqlLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(HqlLearnApplication.class);

    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;
    private static AttemptService attemptService;
    private static ApplicationContext context;

    public static void main(String[] args) throws Exception {

        context = SpringApplication.run(HqlLearnApplication.class, args);

        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);
        attemptService = context.getBean(AttemptService.class);

        System.out.println("\n============ Hands-on 2: HQL Permanent Employees fetch ============");
        testGetAllPermanentEmployees();

        System.out.println("\n============ Hands-on 3: HQL Quiz Attempt Details ============");
        testAttemptDetails();

        System.out.println("\n============ Hands-on 4: HQL Average Salary Calculation ============");
        testGetAverageSalary();

        System.out.println("\n============ Hands-on 5: Native SQL Query ============");
        testGetAllEmployeesNative();

        System.out.println("\n============ Hands-on 6: JPA Criteria Query Builder ============");
        testCriteriaQuery();
    }

    // Hands-on 2
    private static void testGetAllPermanentEmployees() {
        LOGGER.info("Start testGetAllPermanentEmployees");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End testGetAllPermanentEmployees");
    }

    // Hands-on 3
    private static void testAttemptDetails() {
        LOGGER.info("Start testAttemptDetails");
        // Get details of user id 1, attempt id 1
        Attempt attempt = attemptService.getAttempt(1, 1);
        if (attempt != null) {
            System.out.println("User: " + attempt.getUser().getName() + " | Attempt Date: " + attempt.getDate() + " | Score: " + attempt.getScore());
            System.out.println("--------------------------------------------------------------------------------");
            for (AttemptQuestion aq : attempt.getAttemptQuestions()) {
                Question q = aq.getQuestion();
                System.out.println(q.getText());
                int index = 1;
                for (Options opt : q.getOptionsList()) {
                    boolean isSelected = false;
                    for (AttemptOption ao : aq.getAttemptOptions()) {
                        if (ao.getOptions().getId() == opt.getId()) {
                            isSelected = ao.isSelected();
                            break;
                        }
                    }
                    System.out.printf(" %d) %-12s %3.1f     %b\n", index++, opt.getText(), opt.getScore(), isSelected);
                }
                System.out.println();
            }
        } else {
            System.out.println("Attempt details not found!");
        }
        LOGGER.info("End testAttemptDetails");
    }

    // Hands-on 4
    private static void testGetAverageSalary() {
        LOGGER.info("Start testGetAverageSalary");
        double avgSalary = employeeService.getAverageSalary(1); // Department 1
        System.out.println("Average Salary of Department 1: " + avgSalary);
        LOGGER.info("End testGetAverageSalary");
    }

    // Hands-on 5
    private static void testGetAllEmployeesNative() {
        LOGGER.info("Start testGetAllEmployeesNative");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        System.out.println("Employees retrieved via Native SQL Query:");
        employees.forEach(System.out::println);
        LOGGER.info("End testGetAllEmployeesNative");
    }

    // Hands-on 6
    private static void testCriteriaQuery() {
        LOGGER.info("Start testCriteriaQuery");
        EntityManager em = context.getBean(EntityManager.class);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> employee = cq.from(Employee.class);

        // Dynamically add search filters: e.g. permanent = true, salary > 55000.0
        java.util.List<Predicate> predicates = new java.util.ArrayList<>();
        predicates.add(cb.equal(employee.get("permanent"), true));
        predicates.add(cb.greaterThan(employee.get("salary"), 55000.0));

        cq.where(predicates.toArray(new Predicate[0]));

        List<Employee> result = em.createQuery(cq).getResultList();
        System.out.println("Criteria Query results (Filter: Permanent = true AND Salary > 55000):");
        result.forEach(System.out::println);
        LOGGER.info("End testCriteriaQuery");
    }
}
