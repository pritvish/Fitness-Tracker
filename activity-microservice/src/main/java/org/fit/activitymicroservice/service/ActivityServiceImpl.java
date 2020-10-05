package org.fit.activitymicroservice.service;

import org.fit.activitymicroservice.dto.ActivityDto;
import org.fit.activitymicroservice.entity.Activity;
import org.fit.activitymicroservice.repository.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ActivityDto addActivity(Activity activity) {
        Activity savedActivity = activityRepository.save(activity);
        return convertActivityToActivityDto(savedActivity);
    }

    @Override
    public String updateActivity(Activity activity, Date startDate, Date endDate) throws Exception {
        List<Activity> employeeActivitiesByRange = activityRepository.getEmployeeActivitiesByRange(activity.getEmployeeId(), startDate, endDate);
        if (!employeeActivitiesByRange.isEmpty()) {
            Activity updatedActivity = activityRepository.save(activity);
            return "Activity successfully updated for employee id: " + activity.getEmployeeId();
        } else {
            throw new Exception("Employee not found for updation");
        }
    }

    @Override
    public List<ActivityDto> getActivitiesByRange(Date startDate, Date endDate) {
        List<Activity> activitiesByRange = activityRepository.getActivitiesByRange(startDate, endDate);
        return activitiesByRange.stream().map(this::convertActivityToActivityDto).collect(Collectors.toList());
    }

    @Override
    public List<ActivityDto> getEmployeeActivitiesByRange(String employeeId, Date startDate, Date endDate) {
        List<Activity> employeeActivities = null;
        if (startDate == null && endDate == null) {
            employeeActivities = activityRepository.getEmployeeActivitiesWithoutRange(employeeId);
        } else {
            employeeActivities = activityRepository.getEmployeeActivitiesByRange(employeeId, startDate, endDate);
        }
        return employeeActivities.stream().map(this::convertActivityToActivityDto).collect(Collectors.toList());
    }

    @Override
    public List<ActivityDto> getActivitiesByActivityName(String activityName, Date startDate, Date endDate) {
        List<Activity> activitiesByActivityNameAndRange = activityRepository.getActivitiesByActivityNameAndRange(activityName, startDate, endDate);
        return activitiesByActivityNameAndRange.stream().map(this::convertActivityToActivityDto).collect(Collectors.toList());
    }

    @Override // TODO need to use @Modifying and @Transactional
    public String deleteActivity(String employeeId, Date startDate, Date endDate) throws Exception {
        List<Activity> employeeActivitiesByRange = activityRepository.getEmployeeActivitiesByRange(employeeId, startDate, endDate);
        if (!employeeActivitiesByRange.isEmpty()) {
            for (Activity employeeActivityByRange : employeeActivitiesByRange) {
                activityRepository.deleteEmployeeActivity(employeeId, startDate, endDate);// TODO risky code , need to check
            }
            return "Activities successfully deleted for employee id: " ;
        } else {
            throw new Exception("Employee not found to delete");
        }
    }

    ActivityDto convertActivityToActivityDto(Activity activity) {
        ActivityDto activityDto = modelMapper.map(activity, ActivityDto.class);
        return activityDto;
    }

    Activity convertActivityDtoToActivity(ActivityDto activityDto) {
        Activity activity = modelMapper.map(activityDto, Activity.class);
        return activity;
    }
}
