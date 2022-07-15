package com.example.EDMWebsite.Controllers;

import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Services.UserService;
import com.stripe.Stripe;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/donate")
    public String setupStripe(){
        return "donate";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/checkout")
    public String successfulDonation(){
        return "checkout";
    }

    @GetMapping("/profile")
    public String profile(Authentication authUser,Model model){
        System.out.println("THE NAME IS BELOW");
        System.out.println(authUser.getName());
        model.addAttribute("authUser",authUser.getName());
        return "profile";
    }


    @GetMapping("products")
    public String products(){
        return "products";
    }


}
