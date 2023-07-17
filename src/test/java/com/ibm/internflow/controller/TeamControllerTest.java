package com.ibm.internflow.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.ibm.internflow.dto.TeamDto;
import com.ibm.internflow.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TeamControllerTest {
    private TeamService teamService;
    private TeamController teamController;

    @BeforeEach
    public void setUp() {
        teamService = mock(TeamService.class);
        teamController = new TeamController(teamService);
    }

    @Test
    public void testGetTeams() {
        TeamDto team1 = new TeamDto();
        team1.setTeamId(1L);
        team1.setTeamName("Internflow");

        TeamDto team2 = new TeamDto();
        team2.setTeamId(2L);
        team2.setTeamName("COLA");

        List<TeamDto> teamsList = List.of(team1, team2);

        when(teamService.getTeams()).thenReturn(teamsList);

        ResponseEntity<List<TeamDto>> responseEntity = teamController.getTeams();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(teamsList, responseEntity.getBody());

        verify(teamService, times(1)).getTeams();
    }

    @Test
    public void testAddTeam() {
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamName("Internflow");

        when(teamService.addTeam(any(TeamDto.class))).thenReturn(teamDto);

        ResponseEntity<TeamDto> responseEntity = teamController.addTeam(teamDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(teamDto, responseEntity.getBody());

        verify(teamService, times(1)).addTeam(any(TeamDto.class));
    }

    @Test
    public void testDeleteTeamById() {
        Long teamId = 1L;

        teamController.deleteTeamById(teamId);

        verify(teamService, times(1)).deleteById(teamId);
    }
}
