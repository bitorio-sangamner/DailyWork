package com.email.Email_Sending_App2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmailSendingApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(EmailSendingApp2Application.class, args);
	}

}
