package com.lei.tang.dao;

import com.lei.tang.domain.Student;

import java.util.List;

/**
 * @author tanglei
 * @date 18/9/13
 */
public interface StudentDao {

    List<Student> list();

    void saveStudent(Student student);
}
