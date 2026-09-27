package com.example3.ApplicationPropertiesWithManyFieldsDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApplicationPropertiesWithManyFieldsDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context =
				SpringApplication.run(ApplicationPropertiesWithManyFieldsDemoApplication.class, args);
		PaymentService paymentService = context.getBean(PaymentService.class);
		paymentService.pay();

	}

}
