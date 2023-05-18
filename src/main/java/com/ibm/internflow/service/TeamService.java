package com.ibm.internflow.service;

import com.ibm.internflow.Transformer;
import com.ibm.internflow.dto.TeamDto;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.repository.StudentRepository;
import com.ibm.internflow.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public TeamDto addTeam(TeamDto teamDto) {
        var entity = Transformer.fromDto(teamDto);
       // var teamLeader = studentRepository.save(Transformer.fromDto(teamDto.getTeamLeader()));
       // entity.setTeamLeader(teamLeader);
        //entity.getStudents().forEach(student -> student.setTeam(entity));
        var dto = Transformer.toDto(teamRepository.save(entity));
        List<StudentEntity> students = new ArrayList<>();
        teamDto.getStudents().forEach(student ->students.add(studentRepository.getReferenceById(student.getStudentId())));
        students.forEach(student->student.setTeam(entity));
        studentRepository.saveAll(students);
        return dto;
    }
}
