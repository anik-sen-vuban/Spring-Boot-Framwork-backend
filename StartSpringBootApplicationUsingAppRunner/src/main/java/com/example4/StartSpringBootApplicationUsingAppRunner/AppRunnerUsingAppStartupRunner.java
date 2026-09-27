package com.example4.StartSpringBootApplicationUsingAppRunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.stereotype.Component;

@Component
public class AppRunnerUsingAppStartupRunner implements ApplicationRunner {
    private PaymentService paymentService;

    public AppRunnerUsingAppStartupRunner (PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        paymentService.pay();
    }
}
