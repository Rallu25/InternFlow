package com.ibm.internflow.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.ibm.internflow.dto.GradesDto;
import com.ibm.internflow.service.GradesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GradesControllerTest {
    private GradesService gradesService;
    private GradesController gradesController;

    @BeforeEach
    public void setUp() {
        gradesService = mock(GradesService.class);
        gradesController = new GradesController(gradesService);
    }

    @Test
    public void testAddGrades() {
        GradesDto gradesDto = new GradesDto();
        gradesDto.setGradeValue(9);

        when(gradesService.addGrades(any(GradesDto.class))).thenReturn(gradesDto);

        ResponseEntity<GradesDto> responseEntity = gradesController.addGrades(gradesDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(gradesDto, responseEntity.getBody());

        verify(gradesService, times(1)).addGrades(any(GradesDto.class));
    }

    @Test
    public void testGetGrades() {
        GradesDto grade1 = new GradesDto();
        grade1.setGradeId(1L);
        grade1.setGradeValue(8);

        GradesDto grade2 = new GradesDto();
        grade2.setGradeId(2L);
        grade2.setGradeValue(10);

        List<GradesDto> gradesList = List.of(grade1, grade2);

        when(gradesService.getGrades()).thenReturn(gradesList);

        ResponseEntity<List<GradesDto>> responseEntity = gradesController.getGrades();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(gradesList, responseEntity.getBody());

        verify(gradesService, times(1)).getGrades();
    }

}

