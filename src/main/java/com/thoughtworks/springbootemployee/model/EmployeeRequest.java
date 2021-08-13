package com.thoughtworks.springbootemployee.model;

public class EmployeeRequest {

    public String name;
    public Integer age;
    public String gender;
    public Integer salary;
    public Integer companyId;

    public EmployeeRequest(Integer id, String name, Integer age, String gender, Integer salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }//todo

    public EmployeeRequest() {
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

}
