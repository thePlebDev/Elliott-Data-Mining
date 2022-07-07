package com.example.EDMWebsite.Controllers;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //todo:if sign up fails, we just send it back to sign up and give it extra error data
    @PostMapping("/signup")
    public String signup(User user, Authentication auth){
        //The authentication represents the authenticated user.
        //which gets added to the security context after saveUser(user) is called;
        this.userService.saveUser(user);

        return "profile";
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
