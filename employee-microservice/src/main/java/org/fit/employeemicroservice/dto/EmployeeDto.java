package org.fit.employeemicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fit.employeemicroservice.entity.Activity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private String employeeId;
    private String employeeName;
    private List<Activity> activities;
}