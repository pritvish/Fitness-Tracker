package org.fit.employeemicroservice.service;

import org.fit.employeemicroservice.dto.EmployeeDto;
import org.fit.employeemicroservice.entity.Employee;
import org.fit.employeemicroservice.repository.EmployeeRepository;
import org.fit.employeemicroservice.util.EmployeeUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(this::convertEmployeeToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(String employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return convertEmployeeToEmployeeDto(employee.get());
    }

    @Override
    public EmployeeDto createEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return convertEmployeeToEmployeeDto(savedEmployee);
    }

    @Override
    public String updateEmployee(Employee employee) throws Exception {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getEmployeeId());
        if (optionalEmployee.isPresent()) {
            Employee savedEmployee = employeeRepository.save(employee);
            return "Successfully inserted the record with employee id :"+optionalEmployee.get().getEmployeeId();
        } else {
            throw new Exception("Could not find the employee id : "+employee.getEmployeeId());
        }
    }

    @Override
    public String deleteEmployee(String employeeId) throws Exception {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteById(employeeId);
            return "Successfully deleted the record with employee id :"+optionalEmployee.get().getEmployeeId();
        } else {
            throw new Exception("Could not find the employee id : "+employeeId);
        }
    }

    public Employee convertEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return  employee;
    }

    public EmployeeDto convertEmployeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

}
