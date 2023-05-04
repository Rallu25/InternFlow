package com.ibm.internflow.service;

import com.ibm.internflow.Transformer;
import com.ibm.internflow.dto.StudentDto;
import com.ibm.internflow.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
      private final StudentRepository studentRepository;
      private final MentorRepository mentorRepository;
      private final TeamRepository teamRepository;
      private final ActivitiesRepository activitiesRepository;
      private final AttendanceRepository attendanceRepository;
      private final GradesRepository gradesRepository;

      public StudentService(StudentRepository studentRepository, MentorRepository mentorRepository, TeamRepository teamRepository, ActivitiesRepository activitiesRepository, AttendanceRepository attendanceRepository, GradesRepository gradesRepository) {
            this.studentRepository = studentRepository;
            this.mentorRepository = mentorRepository;
            this.teamRepository = teamRepository;
            this.activitiesRepository = activitiesRepository;
            this.attendanceRepository = attendanceRepository;
            this.gradesRepository = gradesRepository;
      }
      public List<StudentDto> getStudents() {
            return studentRepository.findAll().stream().map(Transformer::toDto).toList();

      }
}
