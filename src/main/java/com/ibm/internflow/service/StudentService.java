package com.ibm.internflow.service;

import com.ibm.internflow.Transformer;
import com.ibm.internflow.dto.StudentDto;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
      public void deleteById(Long id) {
            studentRepository.deleteById(id);
      }

      public StudentDto addStudent(StudentDto studentDto) {
            var entity = Transformer.fromDto(studentDto);
            return Transformer.toDto(studentRepository.save(entity));
      }

      public List<StudentDto> getStudentsByTeam(Long teamId) {
            List<StudentEntity> students = studentRepository.findByTeam_TeamId(teamId);
            return students.stream().map(Transformer::toDto).collect(Collectors.toList());
      }

      public void removeStudentFromTeam(Long studentId) {
            StudentEntity student = studentRepository.getReferenceById(studentId);
            student.setTeam(null);
            studentRepository.save(student);
      }

      public void addStudentToTeam(Long teamId, StudentDto studentDto) {
            var entity = Transformer.fromDto(studentDto);
            entity.setTeam(teamRepository.getReferenceById(teamId));
            studentRepository.save(entity);
      }

//      public List<StudentDto> getStudentsByActivity(Long activityId) {
//            List<StudentEntity> students = studentRepository.findByActivities_ActivityId(activityId);
//            return students.stream().map(s -> Transformer.toDto(s, activityId)).collect(Collectors.toList());
//      }

      public List<StudentDto> getStudentsByActivity(Long activityId) {
            List<StudentEntity> students = studentRepository.findByActivities_ActivityId(activityId);

            List<StudentEntity> studentsWithTeam = students.stream()
                    .filter(s -> s.getTeam() != null)
                    .toList();

            return studentsWithTeam.stream()
                    .map(s -> Transformer.toDto(s, activityId))
                    .collect(Collectors.toList());
      }

}
