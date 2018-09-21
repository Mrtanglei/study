package com.lei.tang.dao;

import com.lei.tang.domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.comparator.ComparableComparator;

import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @author tanglei
 * @date 18/9/13
 */
public class StudentDaoSpringJdbcImplTest {

    private StudentDao studentDao = null;

    private ApplicationContext applicationContext = null;

    @Before
    public void setParames() {
        applicationContext = new ClassPathXmlApplicationContext("spring-dao.xml");
        studentDao = (StudentDao) applicationContext.getBean("studentDao");
    }

    public void shutDown() {
        applicationContext = null;
    }

    @Test
    public void list() {
        //年龄大于22岁降序输出
        studentDao.list().stream().sorted(Comparator.comparingInt(Student::getAge).reversed()).filter(student -> student.getAge() > 22).forEach(student -> {
            System.out.println(student);
        });
    }

    @Test
    public void saveStudent() {
        Student student = new Student();
        student.setName("jdbcTemplate测试新增");
        student.setAge(40);
        studentDao.saveStudent(student);
    }
}