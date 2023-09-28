package com.gateWayNew.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.security.Key;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }


}
