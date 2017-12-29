package com.rethinkwebdesign.myteamer.model;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.rethinkwebdesign.myteamer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "players", indexes = {
    @Index(columnList = "name", name = "player_name_hidx")
})

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posted_at")
    private Date postedAt = new Date();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    @JsonGetter("team_id")
    public long getTeamId(){
        return this.team.getId();
    }

    @JsonSetter("team_id")
    public void setTeam(long value) {
        this.team = new Team(value);
    }

//    public void setTeamById(long teamId) {
//        this.team = TeamRepository.newInstance.findOne(teamId);
//    }
}
