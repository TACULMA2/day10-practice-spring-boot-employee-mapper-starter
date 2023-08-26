package com.afs.restapi.service.dto;

import com.afs.restapi.entity.Employee;

import java.util.List;

public class CompanyResponse {
    private Long id;
    private String name;
    private int employeesLength;
    private List<Employee> employees;

    public Long getId() {
        return id;
    }
    public CompanyResponse(){}

    public CompanyResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyResponse(int employeesLength) {
        this.employeesLength = employeesLength;
    }

    public int getEmployeesLength() {
        return employeesLength;
    }

    public void setEmployeesLength(int employeesLength) {
        this.employeesLength = employeesLength;
    }

    public CompanyResponse(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
