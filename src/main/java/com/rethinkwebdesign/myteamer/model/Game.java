package com.rethinkwebdesign.myteamer.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "games")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Game implements Event{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posted_at")
    private Date postedAt = new Date();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt = new Date();

    public Game() {

    }

    public Game(long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "game", orphanRemoval = true)
    private Set<TeamGame> teamGames = new HashSet<>();

    public Set<TeamGame> getTeamGames() {
        return teamGames;
    }

    public void setTeamGames(Set<TeamGame> teamGames) {
        this.teamGames = teamGames;
    }

    @Transient
    private ArrayList<Long> teamIds;

    public ArrayList<Long> getTeamIds(){
        if(this.teamIds != null){
            return this.teamIds;
        }
        ArrayList<Long> ids = new ArrayList<>();
        for(TeamGame tg: this.teamGames){
            ids.add(tg.getTeamId());
        }
        return ids;
    }

    @JsonSetter("teamIds")
    public void setTeamIds(ArrayList<Long> ids){
        for(TeamGame tg: this.getTeamGames()){
            if(!ids.contains(tg.getTeamId())){
                this.removeTeam(tg.getTeamId());
            }
        }
        for(long id: ids){
            if(!this.getTeamIds().contains(id)){
                this.addTeam(id);
            }
        }
        this.teamIds = ids;
    }

    public void addTeamGame(TeamGame tg){
        this.teamGames.add(tg);
    }

    public void removeTeamGame(long teamId){
        for(TeamGame tg: this.getTeamGames()){
            if(teamId == tg.getTeamId()){
                this.teamGames.remove(tg);
            }
        }
    }

    public void clearTeamGames(){
        this.teamGames = new HashSet<>();
    }

    public void addTeamGame(long teamId){
        this.teamGames.add(new TeamGame(new Team(teamId), this));
    }

    public void addTeamGame(Team team){
        this.teamGames.add(new TeamGame(team, this));
    }

    public void addTeam(Team team){

    }

    public void addTeam(long teamId){
        this.addTeamGame(teamId);
    }

    public void removeTeam(long teamId){
        this.removeTeamGame(teamId);
    }

    public long getId() {
        return id;
    }


}
