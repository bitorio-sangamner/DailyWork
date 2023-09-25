package dev.rsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoverServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(EurekaDiscoverServiceMain.class, args);
    }
}