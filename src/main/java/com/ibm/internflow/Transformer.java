package com.ibm.internflow;

import com.ibm.internflow.dto.StudentDto;
import com.ibm.internflow.dto.TeamDto;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.entity.TeamEntity;


public class Transformer {
    public static StudentDto toDto(StudentEntity entity) {
        var dto = new StudentDto();
        dto.setStudentId(entity.getStudentId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setTeam(toDto(entity.getTeam());
        return dto;
  }

  public static StudentEntity fromDto(StudentDto dto){
        var entity = new StudentEntity();
        entity.setStudentId(dto.getStudentId());
        entity.setEmail(entity.getEmail());
        entity.setFirstName(entity.getFirstName());
        entity.setLastName(entity.getLastName());
        entity.setTeam(dto.getTeam());
        return entity;
  }

  public static TeamDto toDto(TeamEntity entity){
        var dto = new TeamDto();
        dto.setTeamId(entity.getTeamId());
        dto.setTeamLeader(toDto(entity.getStudents());
        return dto;
  }
  public static TeamEntity fromDto(TeamDto dto){
        var entity = new TeamEntity();
        entity.setTeamId(dto.getTeamId());
        entity.setStudents(dto.getTeamLeader());
  }
}
