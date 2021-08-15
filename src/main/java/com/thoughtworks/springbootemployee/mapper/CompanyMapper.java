package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapper {
    public Company toEntity(CompanyRequest companyRequest) {
       Company company = new Company();
        BeanUtils.copyProperties(companyRequest, company);

        return company;
    }

    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);

        return companyResponse;
    }

    public List<CompanyResponse> companyToResponses(List<Company> companies) {
        List<CompanyResponse> companyResponses = new ArrayList<>();
        companies.forEach(company -> {
            CompanyResponse companyResponse = new CompanyResponse();
            BeanUtils.copyProperties(company, companyResponse);
            int count = company.getEmployees().size();
            companyResponse.setEmployeesCount(count);
            companyResponses.add(companyResponse);
        });

        return companyResponses;
    }
}
