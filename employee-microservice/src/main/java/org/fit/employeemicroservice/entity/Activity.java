package org.fit.employeemicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    private Integer activityId;
    private Integer employeeId;
    private String activityName;
    private int timeSpent;
    private double distance;
    private Date date;
}
