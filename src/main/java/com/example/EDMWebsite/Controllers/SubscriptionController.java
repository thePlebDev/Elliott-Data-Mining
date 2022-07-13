package com.example.EDMWebsite.Controllers;

import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Services.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

    private StripeService stripeService;

    @Autowired
    public SubscriptionController(StripeService stripeService){
        this.stripeService = stripeService;
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


        return "redirect:/subscription/pricing";
    }

    @GetMapping("/pricing")
    public String pricing(){return "pricingPage";}


}
