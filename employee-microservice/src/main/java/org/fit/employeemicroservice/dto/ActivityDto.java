package org.fit.employeemicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto implements Serializable {

    private Integer activityId;
    private String employeeId;
    private String activityName;
    private int timeSpent;
    private double distance;
    private Date date;
}
