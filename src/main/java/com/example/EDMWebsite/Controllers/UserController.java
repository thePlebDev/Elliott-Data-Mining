package com.example.EDMWebsite.Controllers;

import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //todo:if sign up fails, we just send it back to sign up and give it extra error data
    @PostMapping("/signup")
    public String signup(User user){
        this.userService.saveUser(user);

        return "signup_success";
    }
}
