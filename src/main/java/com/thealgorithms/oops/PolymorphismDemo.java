package com.thealgorithms.oopconcepts;

class Payment {
    void pay() {
        System.out.println("Processing generic payment");
    }
}

class CreditCardPayment extends Payment {
    @Override
    void pay() {
        System.out.println("Payment done via Credit Card");
    }
}

class UpiPayment extends Payment {
    @Override
    void pay() {
        System.out.println("Payment done via UPI");
    }
}

public class PolymorphismExample {
    public static void main(String[] args) {
        Payment p1 = new CreditCardPayment();
        Payment p2 = new UpiPayment();
        Payment p3 = new Payment();

        p1.pay();
        p2.pay();
        p3.pay();
    }
}
