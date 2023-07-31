package com.ibm.internflow.service;

import com.ibm.internflow.dto.StudentDto;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStudents() {
        List<StudentEntity> studentEntities = new ArrayList<>();
        StudentEntity studentEntity1 = new StudentEntity();
        studentEntity1.setStudentId(1L);
        studentEntity1.setFirstName("Laura");
        studentEntity1.setLastName("Pop");

        StudentEntity studentEntity2 = new StudentEntity();
        studentEntity2.setStudentId(2L);
        studentEntity2.setFirstName("Sergiu");
        studentEntity2.setLastName("Radu");

        studentEntities.add(studentEntity1);
        studentEntities.add(studentEntity2);

        when(studentRepository.findAll()).thenReturn(studentEntities);

        List<StudentDto> studentDtos = studentService.getStudents();

        assertEquals(studentEntities.size(), studentDtos.size());
        assertEquals(studentEntities.get(0).getStudentId(), studentDtos.get(0).getStudentId());
        assertEquals(studentEntities.get(0).getFirstName(), studentDtos.get(0).getFirstName());
        assertEquals(studentEntities.get(0).getLastName(), studentDtos.get(0).getLastName());
        assertEquals(studentEntities.get(1).getStudentId(), studentDtos.get(1).getStudentId());
        assertEquals(studentEntities.get(1).getFirstName(), studentDtos.get(1).getFirstName());
        assertEquals(studentEntities.get(1).getLastName(), studentDtos.get(1).getLastName());

    }

    @Test
    public void testAddStudent() {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(null);
        studentDto.setFirstName("Ioana");
        studentDto.setLastName("Maria");

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentId(1L);
        studentEntity.setFirstName("Ioana");
        studentEntity.setLastName("Maria");

        when(studentRepository.save(any())).thenReturn(studentEntity);

        StudentDto addedStudentDto = studentService.addStudent(studentDto);

        assertEquals(studentEntity.getStudentId(), addedStudentDto.getStudentId());
        assertEquals(studentEntity.getFirstName(), addedStudentDto.getFirstName());
        assertEquals(studentEntity.getLastName(), addedStudentDto.getLastName());

    }

    @Test
    public void testDeleteById() {
        Long studentId = 1L;

        studentService.deleteById(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    public void testDeleteByIdThrowsNullPointerException() {
        Long studentId = 1L;

        doThrow(NullPointerException.class).when(studentRepository).deleteById(any());

        assertThrows(NullPointerException.class, () -> {
            studentService.deleteById(studentId);
        });
    }


}
