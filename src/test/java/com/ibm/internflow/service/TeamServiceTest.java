package com.ibm.internflow.service;

import com.ibm.internflow.dto.StudentDto;
import com.ibm.internflow.dto.TeamDto;
import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.entity.TeamEntity;
import com.ibm.internflow.repository.StudentRepository;
import com.ibm.internflow.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private TeamService teamService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddTeam() {
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamName("Team A");

        StudentDto teamLeaderDto = new StudentDto();
        teamLeaderDto.setStudentId(1L);

        StudentDto studentDto1 = new StudentDto();
        studentDto1.setStudentId(2L);

        StudentDto studentDto2 = new StudentDto();
        studentDto2.setStudentId(3L);

        List<StudentDto> students = List.of(studentDto1, studentDto2);

        teamDto.setTeamLeader(teamLeaderDto);
        teamDto.setStudents(students);

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setTeamName("Team A");

        StudentEntity teamLeaderEntity = new StudentEntity();
        teamLeaderEntity.setStudentId(1L);

        StudentEntity studentEntity1 = new StudentEntity();
        studentEntity1.setStudentId(2L);

        StudentEntity studentEntity2 = new StudentEntity();
        studentEntity2.setStudentId(3L);

        Set<StudentEntity> studentEntities = new HashSet<>();
        studentEntities.add(teamLeaderEntity);
        studentEntities.add(studentEntity1);
        studentEntities.add(studentEntity2);

        when(teamRepository.save(any(TeamEntity.class))).thenReturn(teamEntity);
        when(studentRepository.getReferenceById(1L)).thenReturn(teamLeaderEntity);
        when(studentRepository.getReferenceById(2L)).thenReturn(studentEntity1);
        when(studentRepository.getReferenceById(3L)).thenReturn(studentEntity2);

        TeamDto addedTeamDto = teamService.addTeam(teamDto);

        assertEquals("Team A", addedTeamDto.getTeamName());
        assertEquals(1L, addedTeamDto.getTeamLeader().getStudentId());
        assertEquals(3, addedTeamDto.getStudents().size());

        verify(teamRepository, times(1)).save(any(TeamEntity.class));
        verify(studentRepository, times(1)).getReferenceById(1L);
        verify(studentRepository, times(1)).getReferenceById(2L);
        verify(studentRepository, times(1)).getReferenceById(3L);
    }

}
