package com.rethinkwebdesign.myteamer.controller;

import com.rethinkwebdesign.myteamer.model.Team;
import com.rethinkwebdesign.myteamer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/teams")
    public Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @PostMapping("/teams")
    public Team createTeam(@Valid @RequestBody Team team) {
        return teamRepository.save(team);
    }

    // Get a Single Teram
    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable(value = "id") Long teamId) {
        Team team = teamRepository.findOne(teamId);
        if(team == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(team);
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> updateNote(@PathVariable(value = "id") Long teamId,
                                           @Valid @RequestBody Team noteDetails) {
        Team team = teamRepository.findOne(teamId);
        if(team == null) {
            return ResponseEntity.notFound().build();
        }
        if(noteDetails.getName() != null){
            team.setName(noteDetails.getName());
        }
        if(noteDetails.getSport() != null){
            team.setSport(noteDetails.getSport());
        }
        if(noteDetails.getDivision() != null){
            team.setDivision(noteDetails.getDivision());
        }

        Team updatedTeam = teamRepository.save(team);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable(value = "id") Long teamId) {
        Team team = teamRepository.findOne(teamId);
        if(team == null) {
            return ResponseEntity.notFound().build();
        }

        teamRepository.delete(team);
        return ResponseEntity.ok().build();
    }
}
