package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.thoughtworks.springbootemployee.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public
class EmployeesController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    @RequestMapping(params = "gender")
    public List<Employee> getGender(@RequestParam String gender) {
        return employeeService.getByGender(gender);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return getEmployees()
                .stream()
                .skip((long) (pageIndex - 1) * pageSize)
                .limit(pageSize).collect(Collectors.toList());
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.create(employee, this);
    }

    @PutMapping(path = "/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeToBeUpdated) {
        return getEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .map(employee -> employeeService.updateEmployeeInfo(employee, employeeToBeUpdated))
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmployee(@PathVariable Integer id) {
        return getEmployees()
                .removeIf(employee -> employee.getId().equals(id));
    }
}
