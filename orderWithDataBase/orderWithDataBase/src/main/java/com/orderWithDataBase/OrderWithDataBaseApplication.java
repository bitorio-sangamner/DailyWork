package com.orderWithDataBase;

import com.orderWithDataBase.config.DemoTradingWebsocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.orderWithDataBase"})
public class OrderWithDataBaseApplication implements CommandLineRunner {

	@Autowired
	 DemoTradingWebsocketConfig demoTradingWebsocketConfig;

	public static void main(String[] args) {
		SpringApplication.run(OrderWithDataBaseApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		demoTradingWebsocketConfig.loginConnect();

	}
}
