package com.example.EDMWebsite.Controllers;

import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Services.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

    private StripeService stripeService;

    @Autowired
    public SubscriptionController(StripeService stripeService){
        this.stripeService = stripeService;
    }

    @GetMapping("/subscribe")//todo: maybe make this authenticated??
    public String subscribe(){
        return "subscribe";
    }


    @GetMapping("/landing")
    public String landing(Model model){

        model.addAttribute("user", new User());
        return "landingPage";
    }
    @PostMapping("/landing") //THIS IS WHERE WE WANT TO CREATE THE CUSTOMER ID
    public String getUserInfo(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) throws StripeException {

        if(bindingResult.hasErrors()){
            return "landingPage";
        }
        //authenticate the user and move on to the pricing page
        String customerId = this.stripeService.createCustomer(user);


        return "redirect:/profile";
    }

    @GetMapping("/pricing") // I want the pricing page to redirect to the sign up
    public String pricing(Authentication auth){

        return "pricingPage";
    }

    @GetMapping("/pricing/{customerId}")
    public String pricingCustomerId(@PathVariable String customerId){
        return "pricing page";
    }


}
