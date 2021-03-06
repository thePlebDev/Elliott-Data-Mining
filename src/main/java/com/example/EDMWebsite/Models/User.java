package com.example.EDMWebsite.Models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
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


    @Size(min=5,max=30)
    @Column(nullable = false,length = 30)
    private String username;

    @Size(min=8)
    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false,length = 45)
    private String email;

    @Column
    private String customerId;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Authority> authorities = new ArrayList<>();

    //THIS IS THE SOURCE SIDE OF THE RELATIONSHIP
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
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
    public String getCustomerId(){return this.customerId;}

    //SETTERS
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setAuthorities(Authority authorities){this.authorities.add(authorities);}
    public void setCalves(Calf calf){
        this.calves.add(calf);
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCustomerId(String customerId){
        this.customerId = customerId;
    }



    //UTILITY
    public void removeCalf(Calf calf){
        this.calves.remove(calf);
        calf.setUser(null);
    }

}
