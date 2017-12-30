package com.rethinkwebdesign.myteamer.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "contact_infos")
public class ContactInfo {

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

//    @Any(fetch = FetchType.LAZY, metaColumn = @Column(name = "personType"))
//    @AnyMetaDef(idType = "long", metaType = "string",
//        metaValues = {
//            @MetaValue(targetEntity = Coach.class, value = "coach"),
//            @MetaValue(targetEntity = Player.class, value = "player")
//        })
//    @Cascade( { org.hibernate.annotations.CascadeType.ALL } )
//    @JoinColumn(name = "personId")
//    private Person person;

    @Column
    private String mobileNumber;

    @Column
    private String emailAddress;

    @OneToOne(mappedBy = "contactInfo")
    private Player player;

    @OneToOne(mappedBy = "contactInfo")
    private Coach coach;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

}
