package com.ibm.internflow.service;

import com.ibm.internflow.Transformer;
import com.ibm.internflow.dto.TeamDto;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.entity.TeamEntity;
import com.ibm.internflow.repository.StudentRepository;
import com.ibm.internflow.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

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
        teamRepository.deleteById(id);
    }

    @Transactional
    public TeamDto addTeam(TeamDto teamDto) {
        var entity = Transformer.fromDto(teamDto);
        Set<StudentEntity> students = new HashSet<>();
        teamDto.getStudents().forEach(student ->students.add(studentRepository.getReferenceById(student.getStudentId())));
        entity.setStudents(students);
        entity.setTeamLeader(studentRepository.getReferenceById(teamDto.getTeamLeader().getStudentId()));
        teamRepository.save(entity);
        students.forEach(student->student.setTeam(entity));
        studentRepository.saveAll(students);
        return Transformer.toDto(entity);
    }
}
