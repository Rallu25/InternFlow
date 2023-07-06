package com.ibm.internflow.service;

import com.ibm.internflow.Transformer;
import com.ibm.internflow.dto.ActivitiesDto;
import com.ibm.internflow.entity.ActivitiesEntity;
import com.ibm.internflow.entity.AttendanceEntity;
import com.ibm.internflow.entity.GradesEntity;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.repository.ActivitiesRepository;
import com.ibm.internflow.repository.AttendanceRepository;
import com.ibm.internflow.repository.GradesRepository;
import com.ibm.internflow.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ActivitiesService {
    private final ActivitiesRepository activitiesRepository;
    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final GradesRepository gradesRepository;

    public ActivitiesService(ActivitiesRepository activitiesRepository, StudentRepository studentRepository, AttendanceRepository attendanceRepository, GradesRepository gradesRepository){
        this.activitiesRepository = activitiesRepository;
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
        this. gradesRepository = gradesRepository;
    }
    public List<ActivitiesDto> getActivities() {
        return activitiesRepository.findAll().stream().map(Transformer::toDto).toList();
    }

    public ActivitiesDto addActivities(ActivitiesDto activitiesDto) {
        var entity = Transformer.fromDto(activitiesDto);
        entity.setStudents(new HashSet<>(studentRepository.findAll()));
        ActivitiesEntity activity = activitiesRepository.save(entity);
        return Transformer.toDto(activity);
    }

//    public void deleteById(Long id) {
//        activitiesRepository.deleteById(id);
//    }

    public void deleteById(Long id) {
        ActivitiesEntity activity = activitiesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Activity not found"));

        deleteAttendances(activity);
        deleteGrades(activity);

        activitiesRepository.deleteById(id);
    }

    private void deleteAttendances(ActivitiesEntity activity) {
        Set<AttendanceEntity> attendances = activity.getAttendances();
        for (AttendanceEntity attendance : attendances) {
            attendance.setActivity(null);
            attendanceRepository.delete(attendance);
        }
    }

    private void deleteGrades(ActivitiesEntity activity) {
        Set<GradesEntity> grades = activity.getGrades();
        for (GradesEntity grade : grades) {
            grade.setActivity(null);
            gradesRepository.delete(grade);
        }
    }

}
