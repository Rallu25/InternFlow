package com.ibm.internflow;

import com.ibm.internflow.dto.StudentDto;
import com.ibm.internflow.dto.TeamDto;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.entity.TeamEntity;
import com.ibm.internflow.entity.GradesEntity;
import com.ibm.internflow.dto.GradesDto;
import com.ibm.internflow.entity.AttendanceEntity;
import com.ibm.internflow.dto.AttendanceDto;
import com.ibm.internflow.entity.ActivitiesEntity;
import com.ibm.internflow.dto.ActivitiesDto;

import java.util.Objects;
import java.util.Optional;


public class Transformer {
    public static StudentDto toDto(StudentEntity entity) {
        return toDto(entity, null);
    }

    public static StudentDto toDto(StudentEntity entity, Long activityId) {
        var dto = new StudentDto();
        dto.setStudentId(entity.getStudentId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        if (entity.getTeam() != null) {
            dto.setTeam(entity.getTeam().getTeamName());
        }
        Optional<ActivitiesEntity> activity = entity.getActivities()
                .stream().filter(a -> Objects.equals(a.getActivityId(), activityId)).findFirst();
        activity.ifPresent(a -> dto.setAttendance(getAttendance(a)));

        return dto;
    }

    private static String getAttendance(ActivitiesEntity a) {
        return a.getAttendance() == null ? null : a.getAttendance().getStatus();
    }

    public static StudentEntity fromDto(StudentDto dto) {
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

    public static GradesDto toDto(GradesEntity entity) {
        var dto = new GradesDto();
        if (entity != null) {
            dto.setGradeId(entity.getGradeId());
            dto.setGradeValue(entity.getGradeValue());
            dto.setComment(entity.getComment());
        }
        return dto;
    }

    public static GradesEntity fromDto(GradesDto dto) {

        var entity = new GradesEntity();
        entity.setGradeId(dto.getGradeId());
        entity.setGradeValue(dto.getGradeValue());
        entity.setComment(dto.getComment());
        return entity;
    }

    public static AttendanceDto toDto(AttendanceEntity entity) {
        var dto = new AttendanceDto();
        if (entity != null) {
            dto.setAttendanceId(entity.getAttendanceId());
            dto.setStatus(entity.getStatus());
        }
        return dto;
    }

    public static AttendanceEntity fromDto(AttendanceDto dto) {
        var entity = new AttendanceEntity();
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public static ActivitiesDto toDto(ActivitiesEntity entity) {
        var dto = new ActivitiesDto();
        dto.setActivityId(entity.getActivityId());
        dto.setActivityName(entity.getActivityName());
        dto.setGrade(toDto(entity.getGrade()));
        dto.setAttendance(toDto(entity.getAttendance()));
        return dto;
    }

    public static ActivitiesEntity fromDto(ActivitiesDto dto) {
        var entity = new ActivitiesEntity();
        entity.setActivityName(dto.getActivityName());
        return entity;
    }

}
