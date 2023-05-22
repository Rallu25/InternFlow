package com.ibm.internflow.service;

import com.ibm.internflow.Transformer;
import com.ibm.internflow.dto.ActivitiesDto;
import com.ibm.internflow.entity.ActivitiesEntity;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.repository.ActivitiesRepository;
import com.ibm.internflow.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ActivitiesService {
    private final ActivitiesRepository activitiesRepository;
    private final StudentRepository studentRepository;

    public ActivitiesService(ActivitiesRepository activitiesRepository, StudentRepository studentRepository){
        this.activitiesRepository = activitiesRepository;
        this.studentRepository = studentRepository;
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

    public void deleteById(Long id) {
        activitiesRepository.deleteById(id);
    }
}
