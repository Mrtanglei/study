package sms.service;

import sms.model.Student;

import java.util.List;
import java.util.Optional;

/**
 * @author tanglei
 * @date 18/10/29
 */
public interface StudentService {

    List<Student> listStudents();

    Optional<Student> getStudent(String id);

    void addStudent(Student student);

    void add(String id,String name,String group);

    void updateStudent(Student student);

    void deleteStudent(Student student);
}
