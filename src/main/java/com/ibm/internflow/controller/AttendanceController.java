package com.ibm.internflow.controller;


import com.ibm.internflow.dto.AttendanceDto;
import com.ibm.internflow.service.AttendanceService;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AttendanceDto>> getAttendances(){
        return ResponseEntity.ok(attendanceService.getAttendances());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAttendance(@RequestBody AttendanceDto attendanceDto) {
        attendanceService.addAttendance(attendanceDto);
    }

    @Transactional
    @DeleteMapping("/{attendanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttendanceById(@PathVariable("attendanceId") Long attendanceId) {
        attendanceService.deleteById(attendanceId);
    }


}
