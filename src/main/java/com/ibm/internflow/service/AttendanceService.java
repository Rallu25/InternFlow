package com.ibm.internflow.service;

import com.ibm.internflow.Transformer;
import com.ibm.internflow.dto.AttendanceDto;
import com.ibm.internflow.entity.ActivitiesEntity;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ibm.internflow.repository.AttendanceRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository){
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }
    public List<AttendanceDto> getAttendances() {
        return attendanceRepository.findAll().stream().map(Transformer::toDto).toList();
    }

    public AttendanceDto addAttendance(AttendanceDto attendanceDto) {
        var entity = Transformer.fromDto(attendanceDto);
        return Transformer.toDto(attendanceRepository.save(entity));
    }

//    public AttendanceDto addAttendance(Long activityId, AttendanceDto attendanceDto) {
//        var entity = Transformer.fromDto(attendanceDto);
//        Transformer.toDto(attendanceRepository.save(entity));
//        StudentEntity student = studentRepository.getReferenceById(attendanceDto.getStudentId());
//        Optional<ActivitiesEntity> first = student.getActivities().stream().filter(a -> Objects.equals(a.getActivityId(), activityId)).findFirst();
//
//    }

    public void deleteById(Long attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }


}
