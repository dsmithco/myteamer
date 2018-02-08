package com.rethinkwebdesign.myteamer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "teams", indexes = {
        @Index(columnList = "name", name = "team_name_hidx")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String sport;

    @Column
    private String division;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posted_at")
    private Date postedAt = new Date();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt = new Date();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "team")
    private Set<Player> players = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "team")
    private List<Coach> coaches = new ArrayList<Coach>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "team")
    private Set<TeamGame> teamGames = new HashSet<>();

    @JsonIgnore
    public Set<TeamGame> getTeamGames() {
        return teamGames;
    }

    public void setTeamGames(Set<TeamGame> teamGames) {
        this.teamGames = teamGames;
    }

    public Team() {

    }

    public Team(long id) {
        this.id = id;
    }

    public Team(int id) {
        this.id = (long)(int) id;
    }

    public Team(String name, String sport, String division) {
        this.name = name;
        this.sport = sport;
        this.division = division;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @JsonIgnore
    public Iterable<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public ArrayList<Coach> getCoaches() {
        ArrayList<Coach> newCoaches = new ArrayList<Coach>(coaches);
        newCoaches.sort(Comparator.comparing(Coach::getPostedAt));
        return newCoaches;
    }

    public void setCoaches(ArrayList<Coach> coaches) {
        this.coaches = coaches;
    }

    public void addCoach(Coach coach){
        coaches.add(coach);
    }

    public void removeCoach(Coach coach){
        coaches.remove(coach);
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }
}
