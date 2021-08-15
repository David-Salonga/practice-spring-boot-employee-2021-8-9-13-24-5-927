package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeResponse;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    CompanyService companyService;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    CompanyMapper companyMapper;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> getCompanies() {
        return companyMapper.companyToResponses(companyService.getAllCompanies());
    }

    @GetMapping(path = "/{companyId}")
    public CompanyResponse getCompanyById(@PathVariable Integer companyId) {
        return companyMapper.toResponse(companyService.getById(companyId));
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<EmployeeResponse> getCompanyEmployees(@PathVariable Integer companyId) {
        return employeeMapper.employeesToResponse(companyService.getEmployeesById(companyId));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<CompanyResponse> getCompaniesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return companyMapper.companyToResponses(companyService.getByPageIndexAndPageSize(pageIndex, pageSize));
    }

    @PostMapping
    public void addCompany(@RequestBody Company company) {
        companyService.create(company);
    }

    @PutMapping(path = "/{id}")
    public Company updateCompany(@PathVariable Integer id, @RequestBody Company companyToBeUpdated) {
        return companyService.update(id, companyToBeUpdated);
    }

    @DeleteMapping("/{companyId}")
    public boolean deleteEmployee(@PathVariable Integer companyId) {
        return companyService.delete(companyId);
    }
}
