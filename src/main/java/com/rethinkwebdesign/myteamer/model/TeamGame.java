package com.rethinkwebdesign.myteamer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    public TeamGame() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @Column(name = "isHome")
    private boolean isHome;


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    public Long getId() {
        return id;
    }

}
