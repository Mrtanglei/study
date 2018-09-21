package com.lei.tang;

import com.lei.tang.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanglei
 * @date 18/9/14
 */
public class EmployeeServiceTest {

    ApplicationContext applicationContext;

    @Autowired
    EmployeeService employeeService;

    @Before
    public void start() {
        applicationContext = new ClassPathXmlApplicationContext("spring-dao-jpa.xml");
        employeeService = applicationContext.getBean(EmployeeService.class);
    }


    @Test
    public void update() {
        employeeService.update(1, 30);
    }

    @Test
    public void testSave(){
        List<Employee> list = new ArrayList<>();
        Employee employee = null;
        for (int i = 0;i<100;i++){
            employee = new Employee();
            employee.setAge(100-i);
            employee.setName("test"+i);
            list.add(employee);
        }
        employeeService.save(list);
    }

    @Test
    public void testFindAll(){
        Page<Employee> page = employeeService.findAll();
        System.out.println("总页数："+page.getTotalPages());
        System.out.println("总条数："+page.getTotalElements());
        System.out.println("当前页："+page.getNumber());
        System.out.println("当前页数据："+page.getContent());
    }

    @Test
    public void testFind(){
        Page<Employee> page = employeeService.find();
        System.out.println("总页数："+page.getTotalPages());
        System.out.println("总条数："+page.getTotalElements());
        System.out.println("当前页："+page.getNumber());
        System.out.println("当前页数据："+page.getContent());
    }
}