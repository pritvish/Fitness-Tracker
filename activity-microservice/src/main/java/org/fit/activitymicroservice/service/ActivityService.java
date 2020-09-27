package org.fit.activitymicroservice.service;

import org.fit.activitymicroservice.dto.ActivityDto;
import org.fit.activitymicroservice.entity.Activity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ActivityService {

    ActivityDto addActivity(Activity activity);

    String updateActivity(Activity activity, Date startDate, Date endDate) throws Exception;

    List<ActivityDto> getActivitiesByRange(Date startDate, Date endDate);

    List<ActivityDto> getEmployeeActivitiesByRange(String employeeId, Date startDate, Date endDate);

    List<ActivityDto> getActivitiesByActivityName(String activityName, Date startDate, Date endDate);

    String deleteActivity(String employeeId, Date startDate, Date endDate) throws Exception;
}
