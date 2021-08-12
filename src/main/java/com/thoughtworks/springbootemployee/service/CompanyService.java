package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;
    private final RetiringCompanyRepository retiringCompanyRepository;


    public CompanyService(RetiringCompanyRepository retiringCompanyRepository, CompanyRepository companyRepository) {
        this.retiringCompanyRepository = retiringCompanyRepository;
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getById(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Employee> getEmployeesById(Integer companyId) {
        return getById(companyId).getEmployees();
    }

    public List<Company> getByPageIndexAndPageSize(Integer pageIndex, int pageSize) {
        return retiringCompanyRepository.findByPageIndexAndPageSize(pageIndex, pageSize);

    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Company update(int companyId, Company companyToBeUpdated) {
        return retiringCompanyRepository.updateCompany(companyId, companyToBeUpdated);
    }

    public boolean delete(int companyId) {
        return retiringCompanyRepository.deleteCompany(companyId);
    }
}
