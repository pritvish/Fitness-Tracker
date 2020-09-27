package org.fit.employeemicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private Integer activityId;
    private Integer employeeId;
    private String activityName;
    private int timeSpent;
    private double distance;
    private Date date;
}
