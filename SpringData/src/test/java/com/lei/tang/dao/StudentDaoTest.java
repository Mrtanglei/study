package com.lei.tang.dao;

import com.lei.tang.domain.Student;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author tanglei
 * @date 18/9/12
 */
public class StudentDaoTest {

    @Test
    public void list() {
        StudentDao dao = new StudentDao();
        List<Student> list = dao.list();
        list.forEach(student -> {
            System.out.println(student);
        });
    }

    @Test
    public void saveTest() {
        StudentDao dao = new StudentDao();
        Student student = new Student();
        student.setName("测试新增");
        student.setAge(33);
        dao.saveStudent(student);
    }
}