package org.fit.employeemicroservice.util;

import org.fit.employeemicroservice.dto.EmployeeDto;
import org.fit.employeemicroservice.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUtil {

    @Autowired
    private  ModelMapper modelMapper;

    public Employee convertEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return  employee;
    }

    public EmployeeDto convertEmployeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

}
