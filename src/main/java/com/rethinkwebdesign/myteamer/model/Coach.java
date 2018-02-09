package com.rethinkwebdesign.myteamer.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coaches", indexes = {
    @Index(columnList = "firstName", name = "player_first_name_hidx"),
    @Index(columnList = "lastName", name = "player_last_name_hidx")
})

public class Coach implements Comparable<Coach>, Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(
            message = "First name is required"
    )
    @Column
    private String firstName;

    @NotNull
    @Column
    private String lastName;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactInfoId")
    private ContactInfo contactInfo;

    @Transient
    private boolean delete = false;

    public Long getId() { return id; }

    public Team getTeam() { return team; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    @JsonGetter("teamId")
    public long getTeamId(){
        return this.team.getId();
    }

    @JsonSetter("teamId")
    public void setTeam(long value) {
        this.team = new Team(value);
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public boolean isDelete() {
        return delete;
    }

    @JsonSetter("delete")
    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    @Override
    public int compareTo(Coach coach) {
        return (this.getPostedAt().before(coach.getPostedAt()) ? -1 :
                (this.getPostedAt().equals(coach.getPostedAt()) ? 0 : 1));
    }
}
