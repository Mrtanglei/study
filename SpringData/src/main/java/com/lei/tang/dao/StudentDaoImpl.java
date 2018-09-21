package com.lei.tang.dao;

import com.lei.tang.domain.Student;
import com.lei.tang.utils.JDBCUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 采用原始jdbc
 *
 * @author tanglei
 * @date 18/9/12
 */
public class StudentDaoImpl implements StudentDao{

    /**
     * 查询所有学生
     *
     * @return
     */
    @Override
    public List<Student> list() {
        List<Student> students = new ArrayList<Student>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from student");
            resultSet = preparedStatement.executeQuery();
            Student student = null;
            while (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releace(resultSet, connection, preparedStatement);
        }
        return students;
    }

    /**
     * 保存学习信息
     *
     * @param student
     */
    @Override
    public void saveStudent(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("insert into student (name, age) values (?,?)");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releace(null, connection, preparedStatement);
        }
    }
}
