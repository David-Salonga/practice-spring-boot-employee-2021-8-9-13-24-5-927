package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;
    private final RetiringEmployeeRepository retiringEmployeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository, RetiringEmployeeRepository retiringEmployeeRepository) {
        this.employeeRepository = employeeRepository;
        this.retiringEmployeeRepository = retiringEmployeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee create(Employee employee) {
        return (employeeRepository.save(employee));
    }

    public Employee getById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getByGender(String gender) {
        return employeeRepository.findEmployeeByGender(gender);
    }

    public List<Employee> getByPageIndexAndPageSize(Integer pageIndex, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageIndex - 1, pageSize)).getContent();
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeUpdated) {
        Employee employee = getById(employeeId);
        return employeeRepository.save(updateEmployeeInformation(employee, employeeUpdated));
    }

    private Employee updateEmployeeInformation(Employee employee, Employee employeeUpdated) {
        if (employeeUpdated.getAge() != null) {
            employee.setAge(employeeUpdated.getAge());
        }
        if (employeeUpdated.getName() != null) {
            employee.setName(employeeUpdated.getName());
        }
        if (employeeUpdated.getGender() != null) {
            employee.setGender(employeeUpdated.getGender());
        }
        if (employeeUpdated.getSalary() != null) {
            employee.setSalary(employeeUpdated.getSalary());
        }
        return employee;
    }

    public void delete(Integer id) {
        employeeRepository.delete(getById(id));
    }

}
