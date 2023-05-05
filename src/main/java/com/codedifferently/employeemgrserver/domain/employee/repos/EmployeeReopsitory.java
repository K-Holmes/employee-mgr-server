package com.codedifferently.employeemgrserver.domain.employee.repos;

import com.codedifferently.employeemgrserver.domain.employee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeReopsitory extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
