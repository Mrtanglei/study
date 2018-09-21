package com.lei.tang;

import com.lei.tang.domain.Employee;
import com.lei.tang.repository.EmployeeCrudRepository;
import com.lei.tang.repository.EmployeePagingAndSortingRepository;
import com.lei.tang.repository.EmployeeRepository;
import com.lei.tang.repository.EmployeeSpecificationExecutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanglei
 * @date 18/9/14
 */
@Service
public class EmployeeService {

    private static int i = 1;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeCrudRepository employeeCrudRepository;

    @Autowired
    EmployeePagingAndSortingRepository employeePagingAndSortingRepository;

    @Autowired
    EmployeeSpecificationExecutorRepository employeeSpecificationExecutorRepository;

    @Transactional
    public void update(long id, int age) {
        employeeRepository.update(id, age);
    }

    @Transactional
    public void save(List<Employee> employees) {
        employeeCrudRepository.saveAll(employees);
    }

    public Page<Employee> findAll() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 10, sort);
        return employeePagingAndSortingRepository.findAll(pageable);
    }

    public Page<Employee> find() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 10, sort);

        Specification<Employee> specification = new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> condition = new ArrayList<>();
                condition.add(criteriaBuilder.gt(root.get("age"), 50));
                condition.add(criteriaBuilder.like(root.get("name"), "%3%"));
                Predicate[] predicates = new Predicate[condition.size()];
                for (int i=0;i<condition.size();i++){
                    predicates[i]=condition.get(i);
                }
                return criteriaBuilder.and(predicates);
            }
        };
        return employeeSpecificationExecutorRepository.findAll(specification, pageable);
    }
}
