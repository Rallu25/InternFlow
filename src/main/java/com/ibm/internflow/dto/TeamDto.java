package com.ibm.internflow.dto;

public class TeamDto {
    private Long teamId;
    private StudentDto teamLeader;

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
}
