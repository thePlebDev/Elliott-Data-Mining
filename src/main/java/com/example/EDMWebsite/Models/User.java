package com.example.EDMWebsite.Models;

import javax.persistence.*;
import java.util.List;

/**
 * TODO: ADD THE AUTHORITY
 * */
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Authority> authorities;

    /**
     * Hibernate requires a constructor with no arguments for every persistent class
     * **/
    public User() {
    }


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    //GETTERS
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public Long getId(){
        return this.id;
    }

    //SETTERS
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
