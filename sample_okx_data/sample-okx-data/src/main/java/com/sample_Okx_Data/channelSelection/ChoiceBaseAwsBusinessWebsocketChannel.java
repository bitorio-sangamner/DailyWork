package com.sample_Okx_Data.channelSelection;

import com.sample_Okx_Data.config.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@Component
public class ChoiceBaseAwsBusinessWebsocketChannel {

    @Autowired
    WebSocketClient webSocketClient;

    Scanner sc=new Scanner(System.in);
    Iterator<String> itr;
    public void choiceBaseAwsBusinessWebsocketChannelSelection(ArrayList<String> currencyList, ArrayList<String> instrumentTypeList)
    {
        System.out.println("Hello you are in choiceBasePrivateChannelSelection");
        if(currencyList==null)
        {
            System.out.println("true");
        }
        else {
            System.out.println("false");
            System.out.println("Element :"+currencyList);
        }

        while(true)
        {

            System.out.println("Enter your choice number");

            System.out.println("0.deposit info channel");//--->Received message: {"event":"subscribe"
            System.out.println("1.withdrawal info Channel");//--->Received message: {"event":"subscribe"
            System.out.println("2.position channel");//----->"msg":"Wrong URL
            System.out.println("3.balance and position channel");//---->"msg":"Wrong URL
            System.out.println("4.order channel");//---->Received message: {"event":"subscribe"
            System.out.println("5.trade channel");//--->Received message: {"event":"subscribe"
            System.out.println("6.Account channel");//----->"msg":"Wrong URL
            System.out.println("7.Position risk warning");//---->"msg":"Wrong URL
            System.out.println("8.Account greeks channel");//---->"msg":"Wrong URL
            System.out.println("9.Place order");//---->"msg":"Wrong URL

            System.out.println("10.Rfqs channel");//--->Received message: {"event":"subscribe"
            System.out.println("11.Quotes channel");//--->Received message: {"event":"subscribe"
            System.out.println("12.Structure block trades channel");//--->Received message: {"event":"subscribe"
            System.out.println("13.LOGOUT");

            int choice=sc.nextInt();

            switch(choice)
            {
                case 0:
                    webSocketClient.subscribeToPrivateChannel("deposit-info","","","","","","","","","","");
                    break;

                case 1:
                    webSocketClient.subscribeToPrivateChannel("withdrawal-info","","","","","","","","","","");
                    break;

                case 2:
                    webSocketClient.subcribeToPrivatePositionChannel("positions","FUTURES","BTC-USD","BTC-USD-200329");
                    break;

                case 3:
                    webSocketClient.subscribePrivateBalanceAndPosition("balance_and_position");
                    break;

                case 4:
                    webSocketClient.subscribeToPrivateChannel("sprd-orders","BTC-USDT_BTC-USDT-SWAP","","","","","","","","","");
                    break;

                case 5:
                    webSocketClient.subscribeToPrivateChannel("sprd-trades","BTC-USDT_BTC-USDT-SWAP","","","","","","","","","");
                    break;

                case 6:
                    itr=currencyList.iterator();
                    while(itr.hasNext()) {
                        //System.out.println("Element:"+itr.next());
                        webSocketClient.subscribeToPrivateChannel("account",itr.next(),"","","","","","","","","");
                    }
                    break;

                case 7:
                    itr=instrumentTypeList.iterator();
                    while(itr.hasNext())
                    {
                        webSocketClient.subscribeToPrivateChannel("liquidation-warning","","",itr.next(),"","","","","","","");
                    }
                    break;

                case 8:
                    itr=currencyList.iterator();
                    while(itr.hasNext())
                    {
                        webSocketClient.subscribeToPrivateChannel("account-greeks","",itr.next(),"","","","","","","","");
                    }
                    break;

                case 9:
                    webSocketClient.subscribeToPrivateChannel("","","","","1213","order","buy","OKB-USDT","isolated","market","1");
                    break;

                case 10:
                    webSocketClient.subscribeToPrivateChannel("rfqs","","","","","","","","","","");
                    break;

                case 11:
                    webSocketClient.subscribeToPrivateChannel("quotes","","","","","","","","","","");
                    break;

                case 12:
                    webSocketClient.subscribeToPrivateChannel("struc-block-trades","","","","","","","","","","");
                    break;


                case 13:
                    return;

                default:
                    System.out.println("Your choice is wrong!!");
                    break;

            }
        }//while
    }//method
}
