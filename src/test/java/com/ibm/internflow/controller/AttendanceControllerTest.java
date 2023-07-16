package com.ibm.internflow.controller;
import com.ibm.internflow.dto.AttendanceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ibm.internflow.service.AttendanceService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class AttendanceControllerTest {
    @Mock
    private AttendanceService attendanceService;

    @InjectMocks
    private AttendanceController attendanceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAttendance() {
        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setStudentId(1L);
        attendanceDto.setActivityId(1L);
        attendanceDto.setStatus("Present");

        attendanceController.addAttendance(attendanceDto);

        verify(attendanceService, times(1)).addAttendance(any(AttendanceDto.class));
    }
    @Test
    public void testGetAttendances() {
        AttendanceDto attendance1 = new AttendanceDto();
        attendance1.setAttendanceId(1L);
        attendance1.setStudentId(1L);
        attendance1.setActivityId(1L);
        attendance1.setStatus("Present");

        AttendanceDto attendance2 = new AttendanceDto();
        attendance2.setAttendanceId(2L);
        attendance2.setStudentId(2L);
        attendance2.setActivityId(1L);
        attendance2.setStatus("Absent");

        List<AttendanceDto> attendanceList = List.of(attendance1, attendance2);

        when(attendanceService.getAttendances()).thenReturn(attendanceList);

        ResponseEntity<List<AttendanceDto>> responseEntity = attendanceController.getAttendances();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<AttendanceDto> responseBody = responseEntity.getBody();
        assertEquals(attendanceList, responseBody);

        verify(attendanceService, times(1)).getAttendances();
    }

    @Test
    public void testDeleteAttendanceById() {
        Long attendanceId = 1L;

        attendanceController.deleteAttendanceById(attendanceId);

        verify(attendanceService, times(1)).deleteById(attendanceId);
    }

}
