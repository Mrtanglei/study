package com.lei.tang.repository;

import com.lei.tang.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tanglei
 * @date 18/9/19
 */
@Repository
public interface EmployeeCrudRepository extends CrudRepository<Employee,Long> {
}
