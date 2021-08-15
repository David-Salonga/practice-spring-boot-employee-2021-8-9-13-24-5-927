package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.EmployeeResponse;
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
    public List<EmployeeResponse> getEmployees() {
        return employeeMapper.employeesToResponse(employeeService.getAllEmployees());
    }

    @GetMapping(path = "/{id}")
    public EmployeeResponse getEmployee(@PathVariable Integer id) {
        return employeeMapper.toResponse(employeeService.getById(id));
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
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeMapper.toResponse(employeeService.create(employeeMapper.toEntity(employeeRequest)));
    }

    @PutMapping(path = "/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        return employeeMapper.toResponse(employeeService.updateEmployee(id, employeeMapper.toEntity(employeeRequest)));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
    }

}
