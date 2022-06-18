package com.example.EDMWebsite.Controllers;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.net.Webhook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stripe/events")
public class WebHookController {

    private Logger logger = LoggerFactory.getLogger(WebHookController.class);

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @PostMapping
    public String webHooks(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader){
        // This is your test secret API key.
        //stripeApiKey;
        // Replace this endpoint secret with your endpoint's unique secret
        // If you are testing with the CLI, find the secret by running 'stripe listen'
        // If you are using an endpoint defined with the API or dashboard, look in your webhook settings
        // at https://dashboard.stripe.com/webhooks


        if(sigHeader == null){
            return "";
        }
        Event event;



                // Only verify the event if you have an endpoint secret defined.
                // Otherwise use the basic event deserialized with GSON.
                try {
                    event = Webhook.constructEvent(
                            payload, sigHeader, endpointSecret
                    );
                } catch (SignatureVerificationException e) {
                    // Invalid signature
                    logger.info("⚠️  Webhook error while validating signature.");

                    return "";
                }

            // Deserialize the nested object inside the event
            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = null;
            if (dataObjectDeserializer.getObject().isPresent()) {
                stripeObject = dataObjectDeserializer.getObject().get();
            } else {
                // Deserialization failed, probably due to an API version mismatch.
                // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
                // instructions on how to handle this case, or return an error here.
            }
            // Handle the event
            System.out.println(event.getType());
            switch (event.getType()) {
                case "payment_intent.succeeded":
                    PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                   logger.info("Payment for " + paymentIntent.getAmount() + " succeeded.");
                   //TODO: THIS IS WHERE CREATING A SUCCESSFUL USER WOULD HAPPEN
                    // Then define and call a method to handle the successful payment intent.
                    // handlePaymentIntentSucceeded(paymentIntent);
                    break;

                case "payment_method.attached":
                    PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
                    // Then define and call a method to handle the successful attachment of a PaymentMethod.
                    // handlePaymentMethodAttached(paymentMethod);
                    break;
                case "charge.succeeded":
                    //TODO: THIS IS TRIGGERED IF THE PAYMENT IS SUCCESSFUL
                    System.out.println("THE MONEY IS GOOD");
                default:
                    logger.warn("Unhandled event type: " + event.getType());
                    break;
            }

            return "";

    }
    }


