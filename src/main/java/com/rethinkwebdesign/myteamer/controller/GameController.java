package com.rethinkwebdesign.myteamer.controller;

import com.rethinkwebdesign.myteamer.model.Game;
import com.rethinkwebdesign.myteamer.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @GetMapping("/games")
    public Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @PostMapping("/games")
    public Game createGame(@Valid @RequestBody Game game) {
        return gameRepository.save(game);
    }

    // Get a Single Teram
    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable(value = "id") Long gameId) {
        Game game = gameRepository.findOne(gameId);
        if(game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(game);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Game> updateNote(@PathVariable(value = "id") Long gameId,
                                           @Valid @RequestBody Game teamDetails) {
        Game game = gameRepository.findOne(gameId);
        if(game == null) {
            return ResponseEntity.notFound().build();
        }
        Game updatedGame = gameRepository.save(game);
        return ResponseEntity.ok(updatedGame);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable(value = "id") Long gameId) {
        Game game = gameRepository.findOne(gameId);
        if(game == null) {
            return ResponseEntity.notFound().build();
        }

        gameRepository.delete(game);
        return ResponseEntity.ok().build();
    }
}
