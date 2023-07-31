package com.ibm.internflow.service;

import com.ibm.internflow.dto.GradesDto;
import com.ibm.internflow.entity.GradesEntity;
import com.ibm.internflow.repository.GradesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GradesServiceTest {

    @Mock
    private GradesRepository gradesRepository;

    @InjectMocks
    private GradesService gradesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetGrades() {
        GradesEntity grade1 = new GradesEntity();
        grade1.setGradeId(1L);
        grade1.setGradeValue(9);

        GradesEntity grade2 = new GradesEntity();
        grade2.setGradeId(2L);
        grade2.setGradeValue(8);

        List<GradesEntity> gradesEntities = List.of(grade1, grade2);
        when(gradesRepository.findAll()).thenReturn(gradesEntities);

        List<GradesDto> gradesDtos = gradesService.getGrades();

        assertEquals(gradesEntities.size(), gradesDtos.size());
        assertEquals(grade1.getGradeId(), gradesDtos.get(0).getGradeId());
        assertEquals(grade1.getGradeValue(), gradesDtos.get(0).getGradeValue());
        assertEquals(grade2.getGradeId(), gradesDtos.get(1).getGradeId());
        assertEquals(grade2.getGradeValue(), gradesDtos.get(1).getGradeValue());

        verify(gradesRepository, times(1)).findAll();
    }

}
