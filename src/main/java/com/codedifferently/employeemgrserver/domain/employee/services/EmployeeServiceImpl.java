package com.codedifferently.employeemgrserver.domain.employee.services;

import com.codedifferently.employeemgrserver.domain.core.exceptions.ResourceCreationException;
import com.codedifferently.employeemgrserver.domain.core.exceptions.ResourceNotFoundException;
import com.codedifferently.employeemgrserver.domain.employee.models.Employee;
import com.codedifferently.employeemgrserver.domain.employee.repos.EmployeeReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeReopsitory employeeReopsitory;

    @Autowired
    public EmployeeServiceImpl(EmployeeReopsitory employeeReopsitory){
        this.employeeReopsitory = employeeReopsitory;
    }
    @Override
    public Employee create(Employee employee) throws ResourceCreationException {
        Optional<Employee> optional = employeeReopsitory.findByEmail(employee.getEmail());
        if (optional.isPresent()){
            throw new ResourceCreationException("Employee with email exists: "+employee.getEmail());
        }
        return employeeReopsitory.save(employee);
    }

    @Override
    public Employee getById(Long id) throws ResourceNotFoundException {
        Employee employee = employeeReopsitory.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No Employee with id: "+id));
        return employee;
    }

    @Override
    public Employee getByEmail(String email) throws ResourceNotFoundException {
        Employee employee = employeeReopsitory.findByEmail(email).
                orElseThrow(()->new ResourceNotFoundException("No Employee with email: "+email));
        return employee;
    }


    @Override
    public List<Employee> getAll() {
        return employeeReopsitory.findAll();
    }

    @Override
    public Employee update(Long id, Employee employeeDetail) throws ResourceNotFoundException {
        Employee employee = getById(id);
        employee.setFirstName(employeeDetail.getFirstName());
        employee.setLastName(employeeDetail.getLastName());
        employee.setEmail(employeeDetail.getEmail());
        employee = employeeReopsitory.save(employee);
        return employee;
    }

    @Override
    public void delete(Long id) {
        Employee employee = getById(id);
        employeeReopsitory.delete(employee);
    }
}
