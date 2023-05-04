package com.ibm.internflow;

import com.ibm.internflow.dto.StudentDto;
import com.ibm.internflow.entity.StudentEntity;

public class Transformer {
    public static StudentDto toDto(StudentEntity entity) {
        var dto = new StudentDto();
        dto.setStudentId(entity.getStudentId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setTeam(toDto(entity.getTeam()));
  }
}
