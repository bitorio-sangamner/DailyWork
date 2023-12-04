package com.sample_Okx_Data.channelSelection;

import com.sample_Okx_Data.config.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@Component
public class ChoiceBaseChannelSelection {

    Scanner sc=new Scanner(System.in);

    @Autowired
    WebSocketClient webSocketClient;
    public void demoPrivateChannelSelection(ArrayList<String> currencyList)
    {
        while(true)
        {
            System.out.println("Enter your choice number");

            System.out.println("1.Account channel");
            System.out.println("2.Position channel");
            System.out.println("3.Balance and position channel");


            System.out.println("6.LOGOUT");

            int choice=sc.nextInt();

            switch(choice)
            {
                case 1:
                    Iterator<String> itr=currencyList.iterator();
                    while(itr.hasNext()) {
                        webSocketClient.subscribePrivateDemoTradingChannel("account",itr.next(),"","","");
                    }
                    break;

                case 2:
                    webSocketClient.subscribePrivateDemoTradingChannel("positions","","FUTURES","BTC-USD","BTC-USD-200329");
                    break;

                case 3:
                    webSocketClient.subscribePrivateDemoTradingChannel("balance_and_position","","","","");
                    break;


                default:
                    System.out.println("Your choice is wrong!!");

            }
        }
    }//demoPrivateChannelSelection

    public void choiceBasePublicChannelSelection(ArrayList<String> bookChannelList,ArrayList<String>tradeChannelList,ArrayList<String> tickerChannelList) {

        while(true)
        {
            System.out.println("Enter your choice number");

            System.out.println("0.Order Book Channel");
            System.out.println("1.Trade Channel");
            System.out.println("2.Ticker Channel");
            System.out.println("3.LOGOUT");

            int choice=sc.nextInt();

            switch(choice)
            {
                case 0:
                    Iterator<String> iterator= bookChannelList.iterator();

                    while(iterator.hasNext())
                    {
                        String instrument=iterator.next();
                        webSocketClient.subscribePublicChannel(instrument,"books");
                    }

                    break;

                case 1:
                    Iterator<String> iterator2= tradeChannelList.iterator();

                    while(iterator2.hasNext())
                    {
                        String instrument=iterator2.next();
                        webSocketClient.subscribePublicChannel(instrument,"trades");
                    }

                    break;

                case 2:
                    Iterator<String> iterator3=tickerChannelList.iterator();

                    while(iterator3.hasNext())
                    {
                        String instrument=iterator3.next();
                        webSocketClient.subscribePublicChannel(instrument,"tickers");
                    }
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Your choice is wrong!!");



            }
        }
    }//choiceBaseChannelSelection method

    public void choiceBasePrivateChannelSelection()
    {
        while(true)
        {
            System.out.println("Enter your choice number");

            System.out.println("0.deposit info channel");
            System.out.println("1.withdrawal info Channel");
            System.out.println("2.position channel");
            System.out.println("3.balance and position channel");
            System.out.println("4.order channel");
            System.out.println("5.trade channel");
            System.out.println("6.LOGOUT");

            int choice=sc.nextInt();

            switch(choice)
            {
                case 0:
                    webSocketClient.subscribeToPrivateChannel("deposit-info","");
                    break;

                case 1:
                    webSocketClient.subscribeToPrivateChannel("withdrawal-info","");
                    break;

                case 2:
                    webSocketClient.subcribeToPrivatePositionChannel("positions","FUTURES","BTC-USD","BTC-USD-200329");
                    break;

                case 3:
                    webSocketClient.subscribePrivateBalanceAndPosition("balance_and_position");
                    break;

                case 4:
                    webSocketClient.subscribeToPrivateChannel("sprd-orders","BTC-USDT_BTC-USDT-SWAP");
                    break;

                case 5:
                    webSocketClient.subscribeToPrivateChannel("sprd-trades","BTC-USDT_BTC-USDT-SWAP");
                    break;


                case 6:
                    return;

                default:
                    System.out.println("Your choice is wrong!!");
                    break;

            }
        }
    }
}
