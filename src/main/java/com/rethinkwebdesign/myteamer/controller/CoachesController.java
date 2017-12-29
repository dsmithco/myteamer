package com.rethinkwebdesign.myteamer.controller;

import com.rethinkwebdesign.myteamer.model.Coach;
import com.rethinkwebdesign.myteamer.model.Coach;
import com.rethinkwebdesign.myteamer.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CoachesController {

    @Autowired
    CoachRepository coachRepository;

    @GetMapping("/coaches")
    public Iterable<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    @PostMapping("/coaches")
    public Coach createCoach(@Valid @RequestBody Coach coach) {
        return coachRepository.save(coach);
    }

    // Get a Single Teram
    @GetMapping("/coaches/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable(value = "id") Long coachId) {
        Coach coach = coachRepository.findOne(coachId);
        if(coach == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(coach);
    }

    @PutMapping("/coaches/{id}")
    public ResponseEntity<Coach> updateNote(@PathVariable(value = "id") Long coachId,
                                           @Valid @RequestBody Coach teamDetails) {
        Coach coach = coachRepository.findOne(coachId);
        if(coach == null) {
            return ResponseEntity.notFound().build();
        }
        if(teamDetails.getFirstName() != null){
            coach.setFirstName(teamDetails.getFirstName());
        }
        if(teamDetails.getLastName() != null){
            coach.setLastName(teamDetails.getLastName());
        }
        Coach updatedCoach = coachRepository.save(coach);
        return ResponseEntity.ok(updatedCoach);
    }

    @DeleteMapping("/coaches/{id}")
    public ResponseEntity<Coach> deleteCoach(@PathVariable(value = "id") Long coachId) {
        Coach coach = coachRepository.findOne(coachId);
        if(coach == null) {
            return ResponseEntity.notFound().build();
        }

        coachRepository.delete(coach);
        return ResponseEntity.ok().build();
    }
}
