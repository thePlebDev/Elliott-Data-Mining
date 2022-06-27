package com.example.EDMWebsite.Models;

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

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column
    private int weight;

    public Calf(){}

    public Calf (String tagNumber,String details,int weight,Sex sex){
        this.tagNumber = tagNumber;
        this.details = details;
        this.weight = weight;
        this.sex = sex;
        this.dateOfBirth = new Date();
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
}
