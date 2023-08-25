package com.afs.restapi.service.mapper;

import com.afs.restapi.dto.EmployeeReqeust;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.service.EmployeeResponse;
import org.springframework.beans.BeanUtils;

public class EmployeeMapper {
    private EmployeeMapper() {}
    public static Employee toEntity(EmployeeReqeust employeeReqeust) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeReqeust, employee);
        return employee;
    }

    public static EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
    }
}
