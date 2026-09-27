package com.example2.ApplicationPropertiesDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationPropertiesDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context =
				SpringApplication.run(ApplicationPropertiesDemoApplication.class, args);
		PaymentService paymentService = context.getBean(PaymentService.class);
		paymentService.pay();
	}
}