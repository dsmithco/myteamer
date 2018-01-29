package com.rethinkwebdesign.myteamer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "team_games")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeamGame {

    public TeamGame(Team team, Game game) {
        this.setTeam(team);
        this.setGame(game);
    }

    public TeamGame(long teamId, long gameId) {
        this.setTeam(new Team(teamId));
        this.setGame(new Game(gameId));
    }

    public TeamGame(long id) {
        this.id = id;
    }

    public TeamGame() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team team;

    @JsonIgnore
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @JsonSetter("teamId")
    public void setTeam(long teamId) {
        this.team = new Team(teamId);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId")
    private Game game;

    @JsonIgnore
    public Game getGame() {
        return game;
    }


    public void setGame(Game game) {
        this.game = game;
    }

    @JsonSetter("gameId")
    public void setGame(long gameId) {
        this.game = new Game(gameId);
    }

    @Column(name = "isHome")
    private boolean isHome;

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    public long getId() {
        return id;
    }

    public long getTeamId(){
        return getTeam().getId();
    }

    public String getTeamName(){
        return team.getName();
    }

    public long getGameId(){
        return getGame().getId();
    }

}
