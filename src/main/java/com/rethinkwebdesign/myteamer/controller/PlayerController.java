package com.rethinkwebdesign.myteamer.controller;

import com.rethinkwebdesign.myteamer.model.Player;
import com.rethinkwebdesign.myteamer.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/players")
    public Iterable<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @PostMapping("/players")
    public Player createPlayer(@Valid @RequestBody Player player) {
        if(player.getId() != null){
            Player savedPlayer = playerRepository.findOne(player.getId());
            if(savedPlayer.getContactInfo() != null){
                player.getContactInfo().setId(savedPlayer.getContactInfo().getId());
            }
        }
        return playerRepository.save(player);
    }

    // Get a Single Teram
    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable(value = "id") Long playerId) {
        Player player = playerRepository.findOne(playerId);
        if(player == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(player);
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<Player> updateNote(@PathVariable(value = "id") Long playerId,
                                           @Valid @RequestBody Player playerDetails) {
        Player player = playerRepository.findOne(playerId);
        if(player == null) {
            return ResponseEntity.notFound().build();
        }
        if(playerDetails.getFirstName() != null){
            player.setFirstName(playerDetails.getFirstName());
        }
        if(playerDetails.getLastName() != null){
            player.setLastName(playerDetails.getLastName());
        }

        if(playerDetails.getBirthday() != null){
            player.setBirthday(playerDetails.getBirthday());
        }

        if(playerDetails.getJerseyNumber() != null){
            player.setJerseyNumber(playerDetails.getJerseyNumber());
        }

        if(playerDetails.getContactInfo() != null){
            if(playerDetails.getContactInfo() != null && player.getContactInfo() != null){
                playerDetails.getContactInfo().setId(player.getContactInfo().getId());
            }
            player.setContactInfo(playerDetails.getContactInfo());
        }

        Player updatedPlayer = playerRepository.save(player);
        return ResponseEntity.ok(updatedPlayer);
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable(value = "id") Long playerId) {
        Player player = playerRepository.findOne(playerId);
        if(player == null) {
            return ResponseEntity.notFound().build();
        }

        playerRepository.delete(player);
        return ResponseEntity.ok().build();
    }
}
