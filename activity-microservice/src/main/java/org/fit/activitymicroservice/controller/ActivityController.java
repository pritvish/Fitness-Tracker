package org.fit.activitymicroservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.fit.activitymicroservice.dto.ActivityDto;
import org.fit.activitymicroservice.dto.EmployeeDto;
import org.fit.activitymicroservice.employeeFeignClient.EmployeeFeignProxy;
import org.fit.activitymicroservice.entity.Activity;
import org.fit.activitymicroservice.exception.EmployeeNotFoundException;
import org.fit.activitymicroservice.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController("/activity-ops")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private EmployeeFeignProxy employeeFeignProxy;

    @PostMapping("/activity")
    public ResponseEntity<ActivityDto> addActivity(@RequestBody Activity activity) {

        ResponseEntity<EmployeeDto> employee = employeeFeignProxy.getEmployeeById(activity.getEmployeeId());

        if (null != employee.getBody()) {
            ActivityDto activityDto = activityService.addActivity(activity);
            log.info("New activity is inserted successfully ...");
            return new ResponseEntity<ActivityDto>(activityDto, HttpStatus.CREATED);
        } else {
            throw new EmployeeNotFoundException("Employee does not exists in record... kindly add employee before adding activity");
        }
    }

    @PutMapping("/activity")
    public ResponseEntity<String> updateActivity(@RequestBody Activity activity, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) throws Exception {
        String response = activityService.updateActivity(activity, startDate, endDate);
//        return ResponseEntity.ok(response);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/activity/date")
    public ResponseEntity<List<ActivityDto>> getActivitiesByRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) throws Exception {
        List<ActivityDto> activitiesByRange = activityService.getActivitiesByRange(startDate, endDate);
        return new ResponseEntity<List<ActivityDto>>(activitiesByRange, HttpStatus.OK);
    }

    @GetMapping("/activityById/{id}/date")
    public ResponseEntity<List<ActivityDto>> getEmployeeActivitiesByRange(@PathVariable("id") String employeeId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) throws Exception {
        List<ActivityDto> employeeActivitiesByRange = activityService.getEmployeeActivitiesByRange(employeeId, startDate, endDate);
        return new ResponseEntity<List<ActivityDto>>(employeeActivitiesByRange, HttpStatus.OK);
    }

    @GetMapping("/activity/{activityName}/date")
    public ResponseEntity<List<ActivityDto>> getActivitiesByActivityName(@PathVariable("activityName") String activityName, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) throws Exception {
        List<ActivityDto> activitiesByActivityName = activityService.getActivitiesByActivityName(activityName, startDate, endDate);
        return new ResponseEntity<List<ActivityDto>>(activitiesByActivityName, HttpStatus.OK);
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable("id") String employeeId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) throws Exception {
        String response = activityService.deleteActivity(employeeId, startDate, endDate);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}
