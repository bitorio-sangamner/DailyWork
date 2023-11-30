package com.sample_Okx_Data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketConfig {
    @Autowired
    WebSocketClient webSocketClient;
    private final String SERVICE_URL = "wss://ws.okx.com:8443/ws/v5/public";

    public void publicConnect() {
        System.out.println(SERVICE_URL);
        webSocketClient.connection(SERVICE_URL);
    }

}
