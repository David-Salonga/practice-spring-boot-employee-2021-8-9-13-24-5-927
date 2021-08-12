package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String companyName;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    List<Employee> employees;

    public Company(Integer id, String companyName, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;
    }

    public Company(String companyName, List<Employee> employees) {
        this.id = null;
        this.companyName = companyName;
        this.employees = employees;
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company() {
    }

    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompanyName(String name) {
        this.companyName = name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


}
