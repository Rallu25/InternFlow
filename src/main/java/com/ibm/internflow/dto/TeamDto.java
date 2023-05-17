package com.ibm.internflow.dto;

import java.util.List;

public class TeamDto {
    private Long teamId;
    private String teamName;
    private StudentDto teamLeader;
    private List<StudentDto> students;

    public TeamDto() {
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public StudentDto getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(StudentDto teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }
}
