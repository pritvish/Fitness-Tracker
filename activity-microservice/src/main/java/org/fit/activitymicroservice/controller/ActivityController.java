package org.fit.activitymicroservice.controller;

import org.fit.activitymicroservice.dto.ActivityDto;
import org.fit.activitymicroservice.entity.Activity;
import org.fit.activitymicroservice.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController("/activity-ops")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping("/activity")
    public ResponseEntity<ActivityDto> addActivity(@RequestBody Activity activity) {
        ActivityDto activityDto = activityService.addActivity(activity);
        return new ResponseEntity<ActivityDto>(activityDto, HttpStatus.CREATED);
    }

    @PutMapping("/activity")
    public ResponseEntity<String> updateActivity(@RequestBody Activity activity, @RequestParam Date startDate, @RequestParam Date endDate) throws Exception {
        String response = activityService.updateActivity(activity, startDate, endDate);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/activity/date")
    public ResponseEntity<List<ActivityDto>> getActivitiesByRange(@RequestParam Date startDate, @RequestParam Date endDate) throws Exception {
        List<ActivityDto> activitiesByRange = activityService.getActivitiesByRange(startDate, endDate);
        return new ResponseEntity<List<ActivityDto>>(activitiesByRange, HttpStatus.OK);
    }

    @GetMapping("/activityById/{id}/date")
    public ResponseEntity<List<ActivityDto>> getEmployeeActivitiesByRange(@PathVariable("id") String employeeId, @RequestParam(required = false) Date startDate, @RequestParam(required = false) Date endDate) throws Exception {
        List<ActivityDto> employeeActivitiesByRange = activityService.getEmployeeActivitiesByRange(employeeId, startDate, endDate);
        return new ResponseEntity<List<ActivityDto>>(employeeActivitiesByRange, HttpStatus.OK);
    }

    @GetMapping("/activity/{activityName}/date")
    public ResponseEntity<List<ActivityDto>> getActivitiesByActivityName(@PathVariable("activityName") String activityName, @RequestParam Date startDate, @RequestParam Date endDate) throws Exception {
        List<ActivityDto> activitiesByActivityName = activityService.getActivitiesByActivityName(activityName, startDate, endDate);
        return new ResponseEntity<List<ActivityDto>>(activitiesByActivityName, HttpStatus.OK);
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable("id") String employeeId, @RequestParam Date startDate, @RequestParam Date endDate) throws Exception {
        String response = activityService.deleteActivity(employeeId, startDate, endDate);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}
