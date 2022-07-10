package com.example.EDMWebsite.Controllers;

import com.example.EDMWebsite.Exceptions.UsernameAlreadyExistsException;
import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.postgresql.util.PSQLException;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ExceptionHandler({UsernameAlreadyExistsException.class})
    public String usernameAlreadyExistsError(Exception exception,RedirectAttributes model){
        model.addFlashAttribute("usernameError",exception.getMessage());

        return "redirect:/signup";

    }

    //todo:if sign up fails, we just send it back to sign up and give it extra error data
    @PostMapping("/signup")
    public String signup( User user){
        //The authentication represents the authenticated user.
        //which gets added to the security context after saveUser(user) is called;

            this.userService.saveUser(user);

            return "redirect:/profile";

    }

    @GetMapping("/signup")
    public String signupGet(Model model){
        model.addAttribute("users",new User());
        return "signup";
    }


    @GetMapping("/calf/all")
    public String allCalves(Model model,Authentication auth){
        String username = auth.getName();
        List<Calf> calfList = userService.getAllCalves(username);
        model.addAttribute("calves",calfList);



        return "calfAll";
    }

    @GetMapping("calf/delete/{id}")
    public String deleteCalf(RedirectAttributes model, @PathVariable Long id,Authentication auth){

        this.userService.deleteCalfById(id,auth);


        model.addFlashAttribute("tagNumber","Calf deleted");

        return "redirect:/calf/all";
    }
}
