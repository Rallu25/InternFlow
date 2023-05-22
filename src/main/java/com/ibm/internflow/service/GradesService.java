package com.ibm.internflow.service;
import com.ibm.internflow.Transformer;

import com.ibm.internflow.dto.GradesDto;
import com.ibm.internflow.repository.GradesRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service

public class GradesService {
    private final GradesRepository gradesRepository;

    public GradesService(GradesRepository gradesRepository){
        this.gradesRepository = gradesRepository;
    }
    public List<GradesDto> getGrades() {
        return gradesRepository.findAll().stream().map(Transformer::toDto).toList();
    }

    public GradesDto addGrades(GradesDto gradesDto) {
        var entity = Transformer.fromDto(gradesDto);
        return Transformer.toDto(gradesRepository.save(entity));
    }

}
