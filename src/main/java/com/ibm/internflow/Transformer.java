package com.ibm.internflow;

import com.ibm.internflow.dto.StudentDto;
import com.ibm.internflow.dto.TeamDto;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.entity.TeamEntity;

import java.util.stream.Collectors;


public class Transformer {
    public static StudentDto toDto(StudentEntity entity) {
        var dto = new StudentDto();
        dto.setStudentId(entity.getStudentId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        return dto;
  }

  public static StudentEntity fromDto(StudentDto dto){
        var entity = new StudentEntity();
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        return entity;
  }

    public static TeamDto toDto(TeamEntity entity) {
        var dto = new TeamDto();
        dto.setTeamId(entity.getTeamId());
        dto.setTeamName(entity.getTeamName());
        dto.setTeamLeader(toDto(entity.getTeamLeader()));
        dto.setStudents(entity.getStudents().stream().map(Transformer::toDto).toList());
        return dto;
    }

    public static TeamEntity fromDto(TeamDto dto) {
        var entity = new TeamEntity();
        entity.setTeamName(dto.getTeamName());
        return entity;
    }
}
