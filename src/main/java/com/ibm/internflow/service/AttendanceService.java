package com.ibm.internflow.service;

import com.ibm.internflow.Transformer;
import com.ibm.internflow.dto.AttendanceDto;
import com.ibm.internflow.entity.ActivitiesEntity;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.repository.ActivitiesRepository;
import com.ibm.internflow.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ibm.internflow.repository.AttendanceRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final ActivitiesRepository activitiesRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository, ActivitiesRepository activitiesRepository){
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.activitiesRepository = activitiesRepository;

    }
    public List<AttendanceDto> getAttendances() {
        return attendanceRepository.findAll().stream().map(Transformer::toDto).toList();
    }

    public void addAttendance(AttendanceDto attendanceDto) {
        var entity = Transformer.fromDto(attendanceDto);
        StudentEntity student = studentRepository.getReferenceById(attendanceDto.getStudentId());
        Optional<ActivitiesEntity> optionalActivity = student.getActivities().stream().filter(a -> Objects.equals(a.getActivityId(), attendanceDto.getActivityId())).findFirst();
        if (optionalActivity.isPresent()) {
            ActivitiesEntity activity = optionalActivity.get();
            entity.setActivity(activity);
            entity.setStudent(student);
            student.setActivities(addActivity(activity, student));
            studentRepository.save(student);
            attendanceRepository.save(entity);
        }

    }

    private Set<ActivitiesEntity> addActivity(ActivitiesEntity activity, StudentEntity student) {
        Set<ActivitiesEntity> activities = student.getActivities();
        activities.add(activity);
        return activities;
    }

    public void deleteById(Long attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }


}
