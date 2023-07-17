package com.ibm.internflow.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.ibm.internflow.dto.StudentDto;
import com.ibm.internflow.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StudentControllerTest {
    private StudentService studentService;
    private StudentController studentController;

    @BeforeEach
    public void setUp() {
        studentService = mock(StudentService.class);
        studentController = new StudentController(studentService);
    }

    @Test
    public void testAddStudent() {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Raluca");
        studentDto.setLastName("Osman");

        when(studentService.addStudent(any(StudentDto.class))).thenReturn(studentDto);

        ResponseEntity<StudentDto> responseEntity = studentController.addStudent(studentDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(studentDto, responseEntity.getBody());

        verify(studentService, times(1)).addStudent(any(StudentDto.class));
    }

    @Test
    public void testGetStudents() {
        StudentDto student1 = new StudentDto();
        student1.setStudentId(1L);
        student1.setFirstName("Vlad");
        student1.setLastName("Pavel");

        StudentDto student2 = new StudentDto();
        student2.setStudentId(2L);
        student2.setFirstName("Maria");
        student2.setLastName("Popescu");

        List<StudentDto> studentsList = List.of(student1, student2);

        when(studentService.getStudents()).thenReturn(studentsList);

        ResponseEntity<List<StudentDto>> responseEntity = studentController.getStudents();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentsList, responseEntity.getBody());

        verify(studentService, times(1)).getStudents();
    }

    @Test
    public void testDeleteStudentById() {
        Long studentId = 1L;

        studentController.deleteStudentById(studentId);

        verify(studentService, times(1)).deleteById(studentId);
    }

    @Test
    public void testGetStudentsByTeam() {
        Long teamId = 1L;

        StudentDto student1 = new StudentDto();
        student1.setStudentId(1L);
        student1.setFirstName("Maria");
        student1.setLastName("Popescu");

        StudentDto student2 = new StudentDto();
        student2.setStudentId(2L);
        student2.setFirstName("Ioana");
        student2.setLastName("Radu");

        List<StudentDto> studentsList = List.of(student1, student2);

        when(studentService.getStudentsByTeam(teamId)).thenReturn(studentsList);

        ResponseEntity<List<StudentDto>> responseEntity = studentController.getStudentsByTeam(teamId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentsList, responseEntity.getBody());

        verify(studentService, times(1)).getStudentsByTeam(teamId);
    }

    @Test
    public void testRemoveStudentFromTeam() {
        Long studentId = 1L;

        studentController.removeStudentFromTeam(studentId);

        verify(studentService, times(1)).removeStudentFromTeam(studentId);
    }

    @Test
    public void testAddStudentToTeam() {
        Long teamId = 1L;
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Ema");
        studentDto.setLastName("Rusu");

        studentController.addStudentToTeam(teamId, studentDto);

        verify(studentService, times(1)).addStudentToTeam(teamId, studentDto);
    }

    @Test
    public void testGetStudentsByActivity() {
        Long activityId = 1L;

        StudentDto student1 = new StudentDto();
        student1.setStudentId(1L);
        student1.setFirstName("Ioana");
        student1.setLastName("Matei");

        StudentDto student2 = new StudentDto();
        student2.setStudentId(2L);
        student2.setFirstName("Flavius");
        student2.setLastName("Lazar");

        List<StudentDto> studentsList = List.of(student1, student2);

        when(studentService.getStudentsByActivity(activityId)).thenReturn(studentsList);

        ResponseEntity<List<StudentDto>> responseEntity = studentController.getStudentsByActivity(activityId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentsList, responseEntity.getBody());

        verify(studentService, times(1)).getStudentsByActivity(activityId);
    }

    }

