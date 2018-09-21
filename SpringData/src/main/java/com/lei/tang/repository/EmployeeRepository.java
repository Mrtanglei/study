package com.lei.tang.repository;

import com.lei.tang.domain.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author tanglei
 * @date 18/9/14
 */
public interface EmployeeRepository extends Repository<Employee, Long> {

    Employee findByName(String name);

    //where name like ?% and age < ?
    List<Employee> findAllByNameStartingWithAndAgeLessThan(String name, int age);

    //where name like %? and age > ?
    List<Employee> findByNameEndingWithAndAgeGreaterThan(String name, int age);

    //where name in (?,?,?...) and age <= ?
    List<Employee> findByNameInAndAgeLessThanEqual(List<String> names, int age);

    //where name not in(?,?,?...) and age between (?,?)
    List<Employee> findByNameNotInOrAgeBetween(List<String> names, int startAge, int endAge);

    @Query("select e from Employee e where e.name like %?1%")
    List<Employee> listByName1(String name);

    @Query("select e from Employee e where e.name like %:name%")
    List<Employee> listByName2(@Param("name") String name);

    @Query(nativeQuery = true, value = "select * from other_employee where name like %?1%")
    List<Employee> listByName3(String name);

    @Query(nativeQuery = true, value = "select * from other_employee where name like %:name%")
    List<Employee> listByName4(@Param("name") String name);

    @Modifying
    @Query("update Employee o set o.age = :age where o.id = :id")
    void update(@Param("id") long id, @Param("age") int age);
}
