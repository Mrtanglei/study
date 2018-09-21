package com.lei.tang.repository;

import com.lei.tang.domain.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * @author tanglei
 * @date 18/9/14
 */
public class EmployeeRepositoryTest {

    private ApplicationContext applicationContext = null;

    private EmployeeRepository employeeRepository;

    @Before
    public void setParames() {
        applicationContext = new ClassPathXmlApplicationContext("spring-dao-jpa.xml");
        employeeRepository = applicationContext.getBean(EmployeeRepository.class);
    }

    @After
    public void shutDown() {
        applicationContext = null;
    }

    @Test
    public void findByName() {
        Employee employee = employeeRepository.findByName("zhangsan");
        Assert.assertNotNull(employee);
        System.out.println(employee);
    }

    @Test
    public void findAllByNameStartingWithAndAgeLessThan() {
        List<Employee> employees = employeeRepository.findAllByNameStartingWithAndAgeLessThan("test", 21);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void findByNameEndingWithAndAgeGreaterThan() {
        List<Employee> employees = employeeRepository.findByNameEndingWithAndAgeGreaterThan("5", 21);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void findByNameInAndAgeLessThanEqual() {
        List<Employee> employees = employeeRepository.findByNameInAndAgeLessThanEqual(Arrays.asList("test1", "test2"), 21);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void findByNameNotInOrAgeBetween() {
        List<Employee> employees = employeeRepository.findByNameNotInOrAgeBetween(Arrays.asList("test1", "test2"), 20, 22);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void listByName1() {
        List<Employee> employees = employeeRepository.listByName1("test1");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void listByName2() {
        List<Employee> employees = employeeRepository.listByName2("test1");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void listByName3() {
        List<Employee> employees = employeeRepository.listByName3("test1");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void listByName4() {
        List<Employee> employees = employeeRepository.listByName4("test1");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}