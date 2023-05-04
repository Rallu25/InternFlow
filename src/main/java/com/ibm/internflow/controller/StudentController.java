package com.ibm.internflow.controller;

import com.ibm.internflow.dto.StudentDto;
import com.ibm.internflow.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDto>> getStudents(){
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(studentDto));
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentById(@PathVariable("studentId") Long studentId) {
        studentService.deleteById(studentId);
    }



}
