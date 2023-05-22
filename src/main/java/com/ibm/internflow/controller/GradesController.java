package com.ibm.internflow.controller;

import com.ibm.internflow.dto.GradesDto;
import com.ibm.internflow.service.GradesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grade")

public class GradesController {
    private final GradesService gradesService;
    public GradesController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GradesDto>> getGrades(){
        return ResponseEntity.ok(gradesService.getGrades());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GradesDto> addGrades(@RequestBody GradesDto gradesDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradesService.addGrades(gradesDto));
    }
}
