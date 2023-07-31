package com.ibm.internflow.service;
import com.ibm.internflow.dto.ActivitiesDto;
import com.ibm.internflow.entity.ActivitiesEntity;
import com.ibm.internflow.repository.ActivitiesRepository;
import com.ibm.internflow.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        ActivitiesEntity activityEntity1 = new ActivitiesEntity();
        activityEntity1.setActivityId(1L);
        activityEntity1.setActivityName("Session1");
        activityEntity1.setCreationDate(LocalDateTime.now());
        activityEntity1.setActivityDate(LocalDate.now());

        ActivitiesEntity activityEntity2 = new ActivitiesEntity();
        activityEntity2.setActivityId(2L);
        activityEntity2.setActivityName("Session2");
        activityEntity2.setCreationDate(LocalDateTime.now());
        activityEntity2.setActivityDate(LocalDate.now());

        List<ActivitiesEntity> activitiesEntities = List.of(activityEntity1,activityEntity2);

        when(activitiesRepository.findAll()).thenReturn(activitiesEntities);

        var result = activitiesService.getActivities();

        assertEquals(2, result.size());
    }

    @Test
    void testAddActivities() {
        ActivitiesDto activityDto = new ActivitiesDto();
        activityDto.setActivityName("Session1");
        activityDto.setActivityDate(LocalDate.now());

        ActivitiesEntity activityEntity = new ActivitiesEntity();
        activityEntity.setActivityId(1L);
        activityEntity.setActivityName("Session1");
        activityEntity.setActivityDate(LocalDate.now());

        when(activitiesRepository.save(any(ActivitiesEntity.class))).thenReturn(activityEntity);

        ActivitiesDto resultDto = activitiesService.addActivities(activityDto);

        assertEquals(activityEntity.getActivityId(), resultDto.getActivityId());
        assertEquals(activityDto.getActivityName(), resultDto.getActivityName());
        assertEquals(activityDto.getActivityDate(), resultDto.getActivityDate());

    }

    @Test
    public void testDeleteByIdWhenActivityNotFound() {
        Long activityIdToDelete = 1L;

        when(activitiesRepository.findById(activityIdToDelete)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> activitiesService.deleteById(activityIdToDelete));
    }

}



