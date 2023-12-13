package com.sample_Okx_Data.channelSelection;

import com.sample_Okx_Data.config.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@Component
public class ChoiceBaseAwsPrivateWebsocketChannel {

    @Autowired
    WebSocketClient webSocketClient;

    Scanner sc=new Scanner(System.in);
    Iterator<String> itr;

    public void choiceBaseAwsPrivateWebsocketChannel(ArrayList<String> currencyList,ArrayList<String> instrumentTypeList)
    {
        while(true)
        {
            System.out.println("Enter your choice number");

            System.out.println("1.Account channel");
            System.out.println("2.position channel");//---->Received message: {"event":"error","msg":"Wrong URL
            System.out.println("3.balance and position channel");
            System.out.println("4.Position risk warning");//--->Received message: {"event":"error","msg":"Wrong URL
            System.out.println("5.Account greeks channel");
            System.out.println("6.LOGOUT");

            int choice=sc.nextInt();

            switch(choice)
            {
                case 1:
                    itr=currencyList.iterator();
                    while(itr.hasNext()) {
                        //System.out.println("Element:"+itr.next());
                        webSocketClient.subscribeToPrivateChannel("account",itr.next(),"","","","","","","","","");
                    }
                    break;

                case 2:
                    webSocketClient.subcribeToPrivatePositionChannel("positions","FUTURES","BTC-USD","BTC-USD-200329");
                    break;

                case 3:
                    webSocketClient.subscribePrivateBalanceAndPosition("balance_and_position");
                    break;

                case 4:
                    itr=instrumentTypeList.iterator();
                    while(itr.hasNext())
                    {
                        webSocketClient.subscribeToPrivateChannel("liquidation-warning","","",itr.next(),"","","","","","","");
                    }
                    break;

                case 5:
                    itr=currencyList.iterator();
                    while(itr.hasNext())
                    {
                        webSocketClient.subscribeToPrivateChannel("account-greeks","",itr.next(),"","","","","","","","");
                    }
                    break;

                case 6:
                    return;
            }

        }
    }

}
