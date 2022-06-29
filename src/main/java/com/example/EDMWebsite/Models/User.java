package com.example.EDMWebsite.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: ADD THE AUTHORITY
 * */
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    //THIS IS THE SOURCE SIDE OF THE RELATIONSHIP
    @OneToMany(mappedBy = "user")
    private List<Calf> calves = new ArrayList<>();

    /**
     * Hibernate requires a constructor with no arguments for every persistent class
     * **/
    public User() {
    }


    public User(String username, String password,String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //GETTERS
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public List<Authority> getAuthorities(){return this.authorities;}
    public Long getId(){
        return this.id;
    }
    public String getEmail(){
        return this.email;
    }
    public List<Calf> getCalves(){return this.calves;}

    //SETTERS
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setAuthorities(Authority authorities){this.authorities.add(authorities);}
    public void setCalves(Calf calf){
        this.calves = calves;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
