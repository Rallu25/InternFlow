package com.ibm.internflow.dto;

import java.util.List;

public class ActivitiesDto {
    private Long activityId;
    private String activityName;
    private GradesDto grade;
    private List<AttendanceDto> attendances;

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

    public GradesDto getGrade() {
        return grade;
    }

    public void setGrade(GradesDto grade) {
        this.grade = grade;
    }

    public List<AttendanceDto> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<AttendanceDto> attendances) {
        this.attendances = attendances;
    }
}
