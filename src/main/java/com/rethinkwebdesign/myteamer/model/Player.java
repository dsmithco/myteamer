package com.rethinkwebdesign.myteamer.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players", indexes = {
    @Index(columnList = "firstName", name = "player_first_name_hidx"),
    @Index(columnList = "lastName", name = "player_last_name_hidx")
})

public class Player implements Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Column
    private String lastName;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday = new Date();;

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

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

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

    @JsonGetter("birthday")
    public String getBirthday() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(birthday);
    }

    @JsonSetter("birthday")
    public void setBirthday(String birthday) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            this.birthday = formatter.parse(birthday);
            System.out.println("Date is: " + birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @JsonGetter("fullName")
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

    public Date getPostedAt() {
        return postedAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

}
