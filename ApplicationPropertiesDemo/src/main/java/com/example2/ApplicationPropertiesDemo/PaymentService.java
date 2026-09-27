package com.example2.ApplicationPropertiesDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
//    private String providerName = "Bkash"; //Wrong whent taking value from application.properties
//    private int retryCount = 3; //Wrong

    @Value("${payment.provider:Bkash}")
    private String providerName;

    @Value("${payment.retry-count:3}")
    private int retryCount;

//    when too many fields are stay, we will use a PaymentProperties class with using @ConfigurationProperties

//    OR
//    public PaymentService(
//            @Value("${payment.provider:Bkash}") String providerName,
//            @Value("${payment.retry-count:3}") int retryCount
//    ){
//        this.providerName = providerName;
//        this.retryCount = retryCount;
//    }

    public void pay(){
        System.out.println("Payment done using " + providerName);
        System.out.println("Retry count: " + retryCount);
    }
}
