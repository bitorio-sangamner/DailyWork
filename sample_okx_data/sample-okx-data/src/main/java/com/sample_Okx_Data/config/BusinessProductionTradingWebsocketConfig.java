package com.sample_Okx_Data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BusinessProductionTradingWebsocketConfig {

    @Autowired
    WebSocketClient webSocketClient;
    private  String SERVICE_URL = "wss://ws.okx.com:8443/ws/v5/business";

    private  String API_KEY = "5cfcfc33-3fad-4406-ab3e-293ec2e886e5";
    private  String SECRET_KEY = "8549DFAEDE5A6FA62A9138419F997FD7";
    private  String PASSPHRASE = "76c60758-beA16";

    public void loginConnect()
    {
        System.out.println(SERVICE_URL);
        webSocketClient.connection(SERVICE_URL);

        if(API_KEY!=null && !API_KEY.equals("") && SECRET_KEY!=null && !SECRET_KEY.equals("") && PASSPHRASE!=null && !PASSPHRASE.equals(""))
        {
            webSocketClient.loginToOkx(API_KEY, SECRET_KEY, PASSPHRASE);
        }
    }
}
