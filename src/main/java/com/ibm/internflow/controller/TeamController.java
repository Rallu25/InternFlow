package com.ibm.internflow.controller;

import com.ibm.internflow.dto.TeamDto;
import com.ibm.internflow.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")

public class TeamController {
    private final TeamService teamService;
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeamDto>> getTeams(){
        return ResponseEntity.ok(teamService.getTeams());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDto> addTeam(@RequestBody TeamDto teamDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.addTeam(teamDto));
    }

    @DeleteMapping("/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeamById(@PathVariable("teamId") Long teamId) {
        teamService.deleteById(teamId);
    }
}
