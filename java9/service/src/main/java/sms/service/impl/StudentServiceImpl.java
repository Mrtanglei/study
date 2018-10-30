package sms.service.impl;

import sms.model.Student;
import sms.persistence.PersistenceService;
import sms.service.StudentService;

import java.util.List;
import java.util.Optional;

/**
 * @author tanglei
 * @date 18/10/29
 */
public class StudentServiceImpl implements StudentService {

    private PersistenceService persistenceService = PersistenceLoder.persistenceService;

    @Override
    public List<Student> listStudents() {
        return persistenceService.list(Student.class);
    }

    @Override
    public Optional<Student> getStudent(String id) {
        return persistenceService.get(Student.class, id);
    }

    @Override
    public void addStudent(Student student) {
        persistenceService.save(student);
    }

    @Override
    public void add(String id, String name, String group) {
        addStudent(new Student(id,name , group));
    }

    @Override
    public void updateStudent(Student student) {
        persistenceService.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        persistenceService.delete(Student.class, student.getId());
    }
}
