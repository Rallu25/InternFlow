package com.ibm.internflow.service;
import com.ibm.internflow.dto.ActivitiesDto;
import com.ibm.internflow.entity.ActivitiesEntity;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.repository.ActivitiesRepository;
import com.ibm.internflow.repository.StudentRepository;
import com.ibm.internflow.service.ActivitiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ActivitiesServiceTest {

    @Mock
    private ActivitiesRepository activitiesRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private ActivitiesService activitiesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetActivities() {
        ActivitiesEntity activityEntity = new ActivitiesEntity();
        activityEntity.setActivityId(1L);
        activityEntity.setActivityName("Sample Activity");
        activityEntity.setCreationDate(LocalDateTime.now());
        activityEntity.setActivityDate(LocalDate.now());

        when(activitiesRepository.findAll()).thenReturn(Collections.singletonList(activityEntity));

        var result = activitiesService.getActivities();

        assertEquals(1, result.size());
        assertEquals(activityEntity.getActivityId(), result.get(0).getActivityId());
        assertEquals(activityEntity.getActivityName(), result.get(0).getActivityName());
        assertEquals(activityEntity.getActivityDate(), result.get(0).getActivityDate());
    }

    @Test
    void testAddActivities() {
        ActivitiesDto activityDto = new ActivitiesDto();
        activityDto.setActivityName("Sample Activity");
        activityDto.setActivityDate(LocalDate.now());

        ActivitiesEntity activityEntity = new ActivitiesEntity();
        activityEntity.setActivityId(1L);
        activityEntity.setActivityName("Sample Activity");
        activityEntity.setCreationDate(LocalDateTime.now());
        activityEntity.setActivityDate(LocalDate.now());

        when(activitiesRepository.save(any(ActivitiesEntity.class))).thenReturn(activityEntity);

        ActivitiesDto resultDto = activitiesService.addActivities(activityDto);

        assertEquals(activityEntity.getActivityId(), resultDto.getActivityId());
        assertEquals(activityDto.getActivityName(), resultDto.getActivityName());
        assertEquals(activityDto.getActivityDate(), resultDto.getActivityDate());

        verify(activitiesRepository, times(1)).save(any(ActivitiesEntity.class));
    }

}



