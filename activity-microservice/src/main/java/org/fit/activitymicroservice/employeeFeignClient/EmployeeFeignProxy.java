package org.fit.activitymicroservice.employeeFeignClient;

import org.fit.activitymicroservice.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("employee")
public interface EmployeeFeignProxy {

    @GetMapping("/employee")
    ResponseEntity<EmployeeDto> getEmployeeById(@RequestParam String employeeId);
}
