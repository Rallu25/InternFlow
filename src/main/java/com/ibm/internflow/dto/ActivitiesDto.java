package com.ibm.internflow.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ActivitiesDto {
    private Long activityId;
    private String activityName;
    private List<GradesDto> grades;
    private AttendanceDto attendance;
    private LocalDateTime creationDate;
    private LocalDate activityDate;

    public ActivitiesDto() {
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }


    public AttendanceDto getAttendance() {
        return attendance;
    }

    public void setAttendance(AttendanceDto attendance) {
        this.attendance = attendance;
    }

    public List<GradesDto> getGrades() {
        return grades;
    }

    public void setGrades(List<GradesDto> grades) {
        this.grades = grades;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }
}
