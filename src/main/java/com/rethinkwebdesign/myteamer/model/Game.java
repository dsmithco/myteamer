package com.rethinkwebdesign.myteamer.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;


@Entity
@Table(name = "games")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Game implements Event{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posted_at")
    private Date postedAt = new Date();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt = new Date();

    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeamGame> teamGames;

    public Game() {

    }

    public Game(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Set<TeamGame> getTeamGames() {
        return teamGames;
    }


    @JsonSetter("teamGames")
    public void setTeamGames(Set<TeamGame> teamGame) {
        this.teamGames = teamGames;
    }

    @JsonIgnore
    public Set<Team> getTeams(){
        Set<Team> teams = new HashSet<>();
        for(TeamGame tg: this.teamGames){
            teams.add(tg.getTeam());
        }
        return teams;
    }

    public Long getId() {
        return id;
    }

    @Transient
    private ArrayList<Integer> teamIds;

    @JsonSetter("teamIds")
    public void setJsonTeamIds(ArrayList<Integer> teamIds) {
        this.teamIds = teamIds;
    }

    @JsonGetter("teamIds")
    public ArrayList<Integer> getTeamIds(){
        if(teamIds != null){
            return teamIds;
        }
        ArrayList<Integer> currentTeamIds = new ArrayList<>();
        for (TeamGame tg: getTeamGames()) {
            Long teamId = tg.getTeam().getId();
            currentTeamIds.add((int) (long) teamId);
        }
        return currentTeamIds;
    }

    //    @JsonSetter("teamIds")
//    public void setTeamIds(ArrayList<Integer> teamIds){
//        System.out.println(teamIds);
//        Set<TeamGame> teamGames = this.getTeamGames();
//        System.out.println(teamGames);
//        ArrayList<Long> currentTeamIds = new ArrayList<>();
//        System.out.println(currentTeamIds);
//        if(teamGames != null){
//            for(TeamGame tg: teamGames){
//                System.out.println(tg.getId());
//                currentTeamIds.add(tg.getId());
//            }
//            teamIds.removeAll(currentTeamIds);
//        }
//        for(int id: teamIds){
//            TeamGame teamGame = new TeamGame(new Team((long) id), this);
//            System.out.println(teamGame.getTeam().getId());
//        }
//    }
}
