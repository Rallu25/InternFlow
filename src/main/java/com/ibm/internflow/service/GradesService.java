package com.ibm.internflow.service;
import com.ibm.internflow.Transformer;

import com.ibm.internflow.dto.AttendanceDto;
import com.ibm.internflow.dto.GradesDto;
import com.ibm.internflow.entity.ActivitiesEntity;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.repository.ActivitiesRepository;
import com.ibm.internflow.repository.GradesRepository;
import com.ibm.internflow.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class GradesService {
    private final GradesRepository gradesRepository;
    private final StudentRepository studentRepository;
    private final ActivitiesRepository activitiesRepository;

    public GradesService(GradesRepository gradesRepository, StudentRepository studentRepository, ActivitiesRepository activitiesRepository){
        this.gradesRepository = gradesRepository;
        this.studentRepository = studentRepository;
        this.activitiesRepository = activitiesRepository;
    }
    public List<GradesDto> getGrades() {
        return gradesRepository.findAll().stream().map(Transformer::toDto).toList();
    }

    public GradesDto addGrades(GradesDto gradesDto) {
        var entity = Transformer.fromDto(gradesDto);
        StudentEntity student = studentRepository.getReferenceById(gradesDto.getStudentId());
        Optional<ActivitiesEntity> optionalActivity = student.getActivities().stream().filter(a -> Objects.equals(a.getActivityId(), gradesDto.getActivityId())).findFirst();
        if (optionalActivity.isPresent()) {
            ActivitiesEntity activity = optionalActivity.get();
            entity.setActivity(activity);
            entity.setStudent(student);
            gradesRepository.save(entity);
        }
        return Transformer.toDto(gradesRepository.save(entity));
    }



}
