package org.fit.employeemicroservice.controller;

import org.fit.employeemicroservice.dto.EmployeeDto;
import org.fit.employeemicroservice.entity.Employee;
import org.fit.employeemicroservice.exception.EmployeeNotFoundException;
import org.fit.employeemicroservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/employee-ops")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return new ResponseEntity<List<EmployeeDto>>(employees, HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<EmployeeDto> getEmployeeById(@RequestParam String employeeId) {
        EmployeeDto employee = employeeService.getEmployeeById(employeeId);
        if (null != employee){
            return new ResponseEntity<EmployeeDto>(employee, HttpStatus.OK);
        } else {
            throw new EmployeeNotFoundException("Employee does not exists in record... kindly add employee before adding activity");
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody Employee employee) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<EmployeeDto>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/employee")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) throws Exception {
        String updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<String>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/employee")
    public ResponseEntity<String> deleteEmployee(@RequestParam String employeeId) throws Exception {
        String deletedEmployee = employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<String>(deletedEmployee, HttpStatus.OK);
    }
}
