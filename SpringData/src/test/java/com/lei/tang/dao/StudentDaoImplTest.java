package com.lei.tang.dao;

import com.lei.tang.domain.Student;
import org.junit.Test;

import java.util.List;

/**
 * @author tanglei
 * @date 18/9/12
 */
public class StudentDaoImplTest {

    @Test
    public void list() {
        StudentDaoImpl dao = new StudentDaoImpl();
        List<Student> list = dao.list();
        list.forEach(student -> {
            System.out.println(student);
        });
    }

    @Test
    public void saveTest() {
        StudentDaoImpl dao = new StudentDaoImpl();
        Student student = new Student();
        student.setName("测试新增");
        student.setAge(33);
        dao.saveStudent(student);
    }
}