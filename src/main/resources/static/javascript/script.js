let stripe = Stripe('pk_test_VXyyQFowid4YOcK4QRXsb7PG');
let elements = stripe.elements();
let card = elements.create('card');
card.mount('#card-element');