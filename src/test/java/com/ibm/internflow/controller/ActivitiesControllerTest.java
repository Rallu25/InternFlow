package com.ibm.internflow.controller;

import com.ibm.internflow.dto.ActivitiesDto;
import com.ibm.internflow.service.ActivitiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ActivitiesControllerTest {

    @Mock
    private ActivitiesService activitiesService;

    @InjectMocks
    private ActivitiesController activitiesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetActivities() {
        ActivitiesDto activity1 = new ActivitiesDto();
        activity1.setActivityId(1L);
        activity1.setActivityName("Activity 1");

        ActivitiesDto activity2 = new ActivitiesDto();
        activity2.setActivityId(2L);
        activity2.setActivityName("Activity 2");

        List<ActivitiesDto> activitiesList = List.of(activity1, activity2);

        when(activitiesService.getActivities()).thenReturn(activitiesList);

        ResponseEntity<List<ActivitiesDto>> responseEntity = activitiesController.getActivities();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(activitiesList, responseEntity.getBody());

        verify(activitiesService, times(1)).getActivities();
    }

    @Test
    public void testAddActivities() {
        ActivitiesDto activitiesDto = new ActivitiesDto();
        activitiesDto.setActivityName("Sample Activity");

        when(activitiesService.addActivities(any(ActivitiesDto.class))).thenReturn(activitiesDto);

        ResponseEntity<ActivitiesDto> responseEntity = activitiesController.addActivities(activitiesDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(activitiesDto, responseEntity.getBody());

        verify(activitiesService, times(1)).addActivities(any(ActivitiesDto.class));
    }

    @Test
    public void testDeleteActivityById() {
        Long activityId = 1L;

        activitiesController.deleteActivityById(activityId);

        verify(activitiesService, times(1)).deleteById(activityId);
    }
}
