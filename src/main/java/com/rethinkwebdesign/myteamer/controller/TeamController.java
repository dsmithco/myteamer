package com.rethinkwebdesign.myteamer.controller;

import com.rethinkwebdesign.myteamer.model.Coach;
import com.rethinkwebdesign.myteamer.model.Team;
import com.rethinkwebdesign.myteamer.repository.CoachRepository;
import com.rethinkwebdesign.myteamer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CoachRepository coachRepository;

    @GetMapping("/teams")
//    @PreAuthorize("hasAuthority('ADMIN')")
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
                                           @Valid @RequestBody Team teamDetails) {
        Team team = teamRepository.findOne(teamId);
        if(team == null) {
            return ResponseEntity.notFound().build();
        }
        if(teamDetails.getName() != null){
            team.setName(teamDetails.getName());
        }
        if(teamDetails.getSport() != null){
            team.setSport(teamDetails.getSport());
        }
        if(teamDetails.getDivision() != null){
            team.setDivision(teamDetails.getDivision());
        }
//        if(teamDetails.getCoaches() != null){
//            ArrayList<Coach> newCoaches = new ArrayList<>();
//            for(Coach coach: teamDetails.getCoaches()){
//                if(coach.isDelete() == false){
//                    newCoaches.add(coach);
//                }else if (coach.getId() != null){
//                    coachRepository.delete(coach.getId());
//                }
//            }
//            team.setCoaches(newCoaches);
//        }

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
