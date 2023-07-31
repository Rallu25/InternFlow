package com.ibm.internflow.service;

import com.ibm.internflow.dto.AttendanceDto;
import com.ibm.internflow.entity.ActivitiesEntity;
import com.ibm.internflow.entity.AttendanceEntity;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.repository.AttendanceRepository;
import com.ibm.internflow.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AttendanceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private AttendanceService attendanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAttendance() {
        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setStudentId(1L);
        attendanceDto.setActivityId(2L);

        StudentEntity student = new StudentEntity();
        student.setStudentId(1L);

        ActivitiesEntity activity = new ActivitiesEntity();
        activity.setActivityId(2L);

        AttendanceEntity attendanceEntity = new AttendanceEntity();

        Set<ActivitiesEntity> activities = new HashSet<>();
        activities.add(activity);
        student.setActivities(activities);

        when(studentRepository.getReferenceById(1L)).thenReturn(student);
        when(attendanceRepository.save(any(AttendanceEntity.class))).thenReturn(attendanceEntity);
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(student);

        attendanceService.addAttendance(attendanceDto);

        verify(attendanceRepository, times(1)).save(any(AttendanceEntity.class));
    }

    @Test
    public void testDeleteById() {
        Long attendanceId = 1L;

        attendanceService.deleteById(attendanceId);

        verify(attendanceRepository, times(1)).deleteById(attendanceId);
    }

    @Test
    public void testDeleteByIdThrowsNullPointerException() {

        Long attendanceId = 1L;

        doThrow(NullPointerException.class).when(attendanceRepository).deleteById(any());

        assertThrows(NullPointerException.class, () -> {
            attendanceService.deleteById(attendanceId);
        });
    }
}
