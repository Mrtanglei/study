package com.lei.tang.repository;

import com.lei.tang.domain.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tanglei
 * @date 18/9/21
 */
@Repository
public interface EmployeePagingAndSortingRepository extends PagingAndSortingRepository<Employee,Long> {
}
