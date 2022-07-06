package com.example.EDMWebsite.Models;

import com.example.EDMWebsite.Models.EnumModels.CalfStatus;
import com.example.EDMWebsite.Models.EnumModels.Sex;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Calf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_number")
    private String tagNumber;
    @Column
    private String details;

    @Column
    private String cciaNumber;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column
    @Enumerated(EnumType.STRING)
    private CalfStatus status;

    @Column
    private int weight;

    @JoinColumn
    @ManyToOne
    private User user;


    public Calf(){}

    public Calf (String tagNumber,String details,int weight,Sex sex,CalfStatus status){
        this.tagNumber = tagNumber;
        this.details = details;
        this.weight = weight;
        this.sex = sex;
        this.status = status;
        this.dateOfBirth = new Date();
    }

    public Calf (String tagNumber,String details,int weight,Sex sex,CalfStatus status,String cciaNumber){
        this.tagNumber = tagNumber;
        this.details = details;
        this.weight = weight;
        this.sex = sex;
        this.status = status;
        this.dateOfBirth = new Date();
        this.cciaNumber = cciaNumber;
    }

    //GETTERS
    public String getTagNumber(){
        return this.tagNumber;
    }
    public String getDetails(){
        return this.details;
    }
    public Date getDateOfBirth(){
        return this.dateOfBirth;
    }
    public int getWeight(){
        return this.weight;
    }
    public Sex getSex(){
        return this.sex;
    }
    public Long getId(){
        return this.id;
    }
    public CalfStatus getStatus(){return this.status;}
    public String getCciaNumber(){return this.cciaNumber;}


    //SETTERS
    public void setTagNumber(String tagNumber){
        this.tagNumber = tagNumber;
    }
    public void setDetails(String details){
        this.details = details;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setDateOfBirth(Date date){
        this.dateOfBirth = date;
    }
    public void setSex(Sex sex){
        this.sex = sex;
    }
    public void setUser(User user){
        this.user = user;
    }
    public void setStatus(CalfStatus calfStatus){this.status = calfStatus;}
    public void setCciaNumber(String cciaNumber){this.cciaNumber = cciaNumber;}

    /**THIS IS NEEDED FOR THE FORMS*/
    public void setId(Long id){this.id = id;}
}
