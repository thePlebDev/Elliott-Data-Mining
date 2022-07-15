package com.example.EDMWebsite.Services;

import com.example.EDMWebsite.Models.Authority;
import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Repositories.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import com.stripe.param.SubscriptionCreateParams.PaymentSettings.SaveDefaultPaymentMethod;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.SubscriptionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service//TODO: make sure that the Stripe exceptions gets properly handled
public class StripeService {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StripeService(UserRepository userRepository,BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(user);
        return customer.getId();
    }

    private Customer createCustomer(String username,String email) throws StripeException {
        Stripe.apiKey = stripeApiKey;
        //create the customer
        CustomerCreateParams customerParams = CustomerCreateParams
                .builder()
                .setName(username)
                .setEmail(email)
                .build();
        Customer customer = Customer.create(customerParams);
        //create the subscription and put it into INACTIVE MODE
        createSubscription("price_1Kh1oMIUupCxt3YbPd92xUh6",customer.getId());
        return Customer.create(customerParams);

    }

    private String createSubscription(String priceId,String customerId) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        // Automatically save the payment method to the subscription
        // when the first payment is successful.
        SubscriptionCreateParams.PaymentSettings paymentSettings =
                SubscriptionCreateParams.PaymentSettings
                        .builder()
                        .setSaveDefaultPaymentMethod(SaveDefaultPaymentMethod.ON_SUBSCRIPTION)
                        .build();

        // Create the subscription. Note we're expanding the Subscription's
        // latest invoice and that invoice's payment_intent
        // so we can pass it to the front end to confirm the payment
        SubscriptionCreateParams subCreateParams = SubscriptionCreateParams
                .builder()
                .setCustomer(customerId)
                .addItem(
                        SubscriptionCreateParams
                                .Item.builder()
                                .setPrice(priceId)
                                .build()
                )
                .setPaymentSettings(paymentSettings)
                .setPaymentBehavior(SubscriptionCreateParams.PaymentBehavior.DEFAULT_INCOMPLETE)
                .addAllExpand(Arrays.asList("latest_invoice.payment_intent"))
                .build();

        Subscription subscription = Subscription.create(subCreateParams);
        System.out.println(subscription.getLatestInvoiceObject().getPaymentIntentObject().getClientSecret());
         return"";

    }


}
