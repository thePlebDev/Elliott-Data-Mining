package com.example.EDMWebsite.Services;

import com.example.EDMWebsite.Models.Authority;
import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Repositories.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service//TODO: make sure that the Stripe exceptions gets properly handled
public class StripeService {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    private UserRepository userRepository;

    @Autowired
    public StripeService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public String createCustomer(User user) throws StripeException {

        //creates the customer
        Customer customer =createCustomer(user.getUsername(),user.getEmail());
        //adds customer id
        user.setCustomerId(customer.getId());

        //setting the authority
        Authority authority = new Authority("NOTPAYED");
        authority.setUser(user);
        user.setAuthorities(authority);

        this.userRepository.save(user);
        return customer.getId();
    }

    private Customer createCustomer(String username,String email) throws StripeException {
        Stripe.apiKey = stripeApiKey;
        CustomerCreateParams customerParams = CustomerCreateParams
                .builder()
                .setName(username)
                .setEmail(email)
                .build();
        return Customer.create(customerParams);

    }


}
