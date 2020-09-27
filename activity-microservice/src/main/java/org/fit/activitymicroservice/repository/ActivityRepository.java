package org.fit.activitymicroservice.repository;

import org.fit.activitymicroservice.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Integer> {

    @Query(nativeQuery = true, value = "select * from fitness_tracker.activity where date between :startDate and :endDate")
    List<Activity> getActivitiesByRange(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    @Query(nativeQuery = true, value = "select * from fitness_tracker.activity where employeeId = :employeeId and date between :startDate and :endDate")
    List<Activity> getEmployeeActivitiesByRange(@Param("employeeId") String employeeId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    @Query(nativeQuery = true, value = "select * from fitness_tracker.activity where activityName = :activityName and date between :startDate and :endDate")
    List<Activity> getActivitiesByActivityNameAndRange(@Param("activityName") String activityName,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    @Query(nativeQuery = true, value = "delete from fitness_tracker.activity where employeeId = :employeeId and date between :startDate and :endDate")
    void deleteEmployeeActivity(@Param("employeeId") String employeeId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
}
