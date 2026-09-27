package com.example3.ApplicationPropertiesWithManyFieldsDemo;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    private PaymentProperties paymentProperties;

    public PaymentService(PaymentProperties paymentProperties){
        this.paymentProperties = paymentProperties;
    }

    public void pay(){
        System.out.println("Payment done using " + paymentProperties.getProvider());
        System.out.println("Retry count: " + paymentProperties.getRetryCount());
        System.out.println("Enabled: " + paymentProperties.isEnabled());
        System.out.println("Timeout: " + paymentProperties.getTimeout());
    }
}
