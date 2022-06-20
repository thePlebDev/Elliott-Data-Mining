package com.example.EDMWebsite.Models;

import javax.persistence.*;

@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @JoinColumn
    @ManyToOne
    private User user;


    public Authority(){}

    public Authority(String name){
        this.name = name;
    }

    //GETTERS
    public String getName(){
        return this.name;
    }
    public Long getId(){
        return this.id;
    }
    public User getUser(){
        return this.user;
    }


    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setUser(User user){
        this.user = user;
    }
}
