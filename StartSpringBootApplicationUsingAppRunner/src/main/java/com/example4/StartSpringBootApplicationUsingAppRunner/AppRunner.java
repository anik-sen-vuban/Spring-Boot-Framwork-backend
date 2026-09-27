package com.example4.StartSpringBootApplicationUsingAppRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class AppRunner implements CommandLineRunner {
    private PaymentService paymentService;

    public AppRunner(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @Override
    public void run(String... args) throws Exception {
        paymentService.pay();
    }
}
