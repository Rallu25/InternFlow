package com.ibm.internflow.service;

import com.ibm.internflow.Transformer;
import com.ibm.internflow.dto.TeamDto;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.entity.TeamEntity;
import com.ibm.internflow.repository.StudentRepository;
import com.ibm.internflow.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;
    private final StudentRepository studentRepository;
    public TeamService(TeamRepository teamRepository, StudentRepository studentRepository){
        this.teamRepository = teamRepository;
        this.studentRepository = studentRepository;
    }
    public List<TeamDto> getTeams(){
        return teamRepository.findAll().stream().map(Transformer::toDto).toList();
    }

    public void deleteById(Long id) {
        List<StudentEntity> students = studentRepository.findByTeam_TeamId(id);
        students.forEach(student -> student.setTeam(null));
        studentRepository.saveAll(students);
        teamRepository.deleteById(id);
    }

    public TeamDto addTeam(TeamDto teamDto) {
        var entity = Transformer.fromDto(teamDto);
        Set<StudentEntity> students = new HashSet<>();
        teamDto.getStudents().forEach(student ->students.add(studentRepository.getReferenceById(student.getStudentId())));
        entity.setStudents(students);
        StudentEntity teamLeader = studentRepository.getReferenceById(teamDto.getTeamLeader().getStudentId());
        entity.setTeamLeader(teamLeader);
        teamRepository.save(entity);
        setTeamOnStudent(entity, students, teamLeader);
        return Transformer.toDto(entity);
    }

    private void setTeamOnStudent(TeamEntity entity, Set<StudentEntity> students, StudentEntity teamLeader) {
        teamLeader.setTeam(entity);
        students.add(teamLeader);
        students.forEach(student->student.setTeam(entity));
        studentRepository.saveAll(students);
    }
}
