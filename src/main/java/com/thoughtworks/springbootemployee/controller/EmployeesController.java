package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeRequest;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public
class EmployeesController {

    @Autowired
    private final EmployeeService employeeService;

    @Autowired
    EmployeeMapper employeeMapper;

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
        return employeeService.getByPageIndexAndPageSize(pageIndex, pageSize);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.create(employeeMapper.toEntity(employeeRequest));
    }

    @PutMapping(path = "/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.updateEmployee(id, employeeMapper.toEntity(employeeRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
    }
}
