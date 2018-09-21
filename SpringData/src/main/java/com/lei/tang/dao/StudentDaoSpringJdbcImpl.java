package com.lei.tang.dao;

import com.lei.tang.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 采用spring JdbcTemplate
 *
 * @author tanglei
 * @date 18/9/13
 */
public class StudentDaoSpringJdbcImpl implements StudentDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> list() {
        List<Student> students = new ArrayList<Student>();
        jdbcTemplate.query("select * from student", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                students.add(student);
            }
        });
        return students;
    }

    @Override
    public void saveStudent(Student student) {
        jdbcTemplate.update("insert into student (name, age) values (?,?)", new Object[]{student.getName(), student.getAge()});
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
