package com.sample_Okx_Data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrivateWebsocketConfig {

    @Autowired
    WebSocketClient webSocketClient;

//    private  String SERVICE_URL = "wss://ws.okx.com:8443/ws/v5/business";
//    private  String API_KEY = "5cfcfc33-3fad-4406-ab3e-293ec2e886e5";
//    private  String SECRET_KEY = "8549DFAEDE5A6FA62A9138419F997FD7";
//    private  String PASSPHRASE = "76c60758-beA16";


    //Demo Trading details
    private  String SERVICE_URL = "wss://wspap.okx.com:8443/ws/v5/private";
    private  String API_KEY = "3cdc01cc-9f1a-4238-8cd1-93f4f807d696";
    private  String SECRET_KEY = "5C8F733918E547FD6813B71A9234AAF1";
    private  String PASSPHRASE = "75c60758-beB16";



    public void loginConnect()
    {
        System.out.println(SERVICE_URL);
        webSocketClient.connection(SERVICE_URL);

        if (API_KEY != null && !API_KEY.equals("")) {
            webSocketClient.loginToOkx(API_KEY, SECRET_KEY, PASSPHRASE);
        }
    }
}
