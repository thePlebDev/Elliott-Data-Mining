package com.example.EDMWebsite.Controllers;

import com.stripe.Stripe;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
