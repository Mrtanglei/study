package com.lei.tang.repository;

import com.lei.tang.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author tanglei
 * @date 18/9/21
 */
public interface EmployeeSpecificationExecutorRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {
}
