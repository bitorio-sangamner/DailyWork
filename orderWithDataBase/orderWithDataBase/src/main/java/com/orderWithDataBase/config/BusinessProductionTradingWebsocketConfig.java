package com.orderWithDataBase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BusinessProductionTradingWebsocketConfig {

    @Autowired
    WebsocketClient webSocketClient;
    //private  String SERVICE_URL = "wss://wsaws.okx.com:8443/ws/v5/business";
    //"wss://ws.okx.com:8443/ws/v5/business";
    //Private WebSocket: wss://ws.okx.com:8443/ws/v5/private
    //Private WebSocket: wss://wsaws.okx.com:8443/ws/v5/private
    private  String SERVICE_URL = "wss://ws.okx.com:8443/ws/v5/private";


    private  String API_KEY = "e96a2dc2-daa0-478f-a01d-0163724c8980";
    private  String SECRET_KEY = "5A777205F09CD3C53FA8EE0906D18058";
    private  String PASSPHRASE = "75c60758-beA18";

    public void loginConnect()
    {
        System.out.println(SERVICE_URL);
        webSocketClient.connection(SERVICE_URL);

        if(API_KEY!=null && !API_KEY.equals("") && SECRET_KEY!=null && !SECRET_KEY.equals("") && PASSPHRASE!=null && !PASSPHRASE.equals(""))
        {
            webSocketClient.loginToOkx(API_KEY, SECRET_KEY, PASSPHRASE);
        }

        orderChannel();
    }

    public void orderChannel()
    {
        //webSocketClient.subscribeAlgoOrderChannel("orders-algo","FUTURES",null,null);
        //webSocketClient.subscribeAlgoOrderChannel();
        //webSocketClient.subscribeOrderChannel("orders","FUTURES","LTC-USDT","LTC-USDT-240628");
    }
}
