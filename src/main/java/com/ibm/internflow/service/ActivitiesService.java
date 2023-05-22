package com.ibm.internflow.service;

import com.ibm.internflow.Transformer;
import com.ibm.internflow.dto.ActivitiesDto;
import com.ibm.internflow.repository.ActivitiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class ActivitiesService {
    private final ActivitiesRepository activitiesRepository;

    public ActivitiesService(ActivitiesRepository activitiesRepository){
        this.activitiesRepository = activitiesRepository;
    }
    public List<ActivitiesDto> getActivities() {
        return activitiesRepository.findAll().stream().map(Transformer::toDto).toList();
    }

    public ActivitiesDto addActivities(ActivitiesDto activitiesDto) {
        var entity = Transformer.fromDto(activitiesDto);
        return Transformer.toDto(activitiesRepository.save(entity));
    }

    @Transactional
    public void deleteById(Long id) {
        activitiesRepository.deleteById(id);
    }
}
