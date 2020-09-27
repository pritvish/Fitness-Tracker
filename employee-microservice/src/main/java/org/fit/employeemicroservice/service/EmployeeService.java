package org.fit.employeemicroservice.service;

import org.fit.employeemicroservice.dto.EmployeeDto;
import org.fit.employeemicroservice.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(String employeeId);

    EmployeeDto createEmployee(Employee employee);

    String updateEmployee(Employee employee) throws Exception;

    String deleteEmployee(String employeeId) throws Exception;

}
