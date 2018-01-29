package com.rethinkwebdesign.myteamer.controller;

import com.rethinkwebdesign.myteamer.model.Game;
import com.rethinkwebdesign.myteamer.model.Team;
import com.rethinkwebdesign.myteamer.model.TeamGame;
import com.rethinkwebdesign.myteamer.repository.GameRepository;
import com.rethinkwebdesign.myteamer.repository.TeamGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamGameRepository teamGameRepository;

    @GetMapping("/games")
    public Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @PostMapping(value = "/games")
    public Game createGame(@Valid @RequestBody Game game) {
        Game newGame =  gameRepository.save(game);
        return newGame;
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
                                           @Valid @RequestBody Game gameDetails) {
        Game game = gameRepository.findOne(gameId);
        if(game == null) {
            return ResponseEntity.notFound().build();
        }

        if(gameDetails.getTeamIds() != null){
            game.setTeamIds(gameDetails.getTeamIds());
        }

        return ResponseEntity.ok(gameRepository.save(game));
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

    private void setTeamGameRepository(ArrayList<Long> teamIds, Game game){
    }

    private void setTeamIds(ArrayList<Long> teamIds){

    }

}
