package com.ibm.internflow.controller;

import com.ibm.internflow.dto.ActivitiesDto;
import com.ibm.internflow.service.ActivitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")

public class ActivitiesController {
    private final ActivitiesService activitiesService;
    public ActivitiesController(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActivitiesDto>> getActivities(){
        return ResponseEntity.ok(activitiesService.getActivities());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActivitiesDto> addActivities(@RequestBody ActivitiesDto activitiesDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activitiesService.addActivities(activitiesDto));
    }

    @Transactional
    @DeleteMapping("/{activityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActivityById(@PathVariable("activityId") Long activityId) {
        activitiesService.deleteById(activityId);
    }
}
