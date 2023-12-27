package com.orderWithDataBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.orderWithDataBase"})
public class OrderWithDataBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderWithDataBaseApplication.class, args);
	}

}
