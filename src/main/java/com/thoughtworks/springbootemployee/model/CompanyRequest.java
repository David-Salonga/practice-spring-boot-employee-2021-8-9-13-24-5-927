package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.List;


public class CompanyRequest {


    String companyName;
    List<Employee> employees;

    public CompanyRequest() {
    }


    public String getCompanyName() {
        return companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setCompanyName(String name) {
        this.companyName = name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


}
