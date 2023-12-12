package com.sample_Okx_Data.channelSelection;

import com.sample_Okx_Data.config.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@Component
public class ChoiceBasePublicChannelSelection {

    @Autowired
    WebSocketClient webSocketClient;

    Scanner sc=new Scanner(System.in);
    public void choiceBasePublicChannelSelection(ArrayList<String> bookChannelList, ArrayList<String>tradeChannelList, ArrayList<String> tickerChannelList)
    {

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
}
