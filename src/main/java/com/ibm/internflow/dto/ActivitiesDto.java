package com.ibm.internflow.dto;

import java.util.List;

public class ActivitiesDto {
    private Long activityId;
    private String activityName;
    private List<GradesDto> grades;
    private AttendanceDto attendance;

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
}
