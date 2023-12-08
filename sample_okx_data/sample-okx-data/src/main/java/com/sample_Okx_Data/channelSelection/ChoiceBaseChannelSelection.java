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

    Iterator<String> itr;
    public void demoPrivateChannelSelection(ArrayList<String> currencyList,ArrayList<String> instrumentTypeList)
    {
        System.out.println("currencyList :"+currencyList);
        while(true)
        {
            System.out.println("Enter your choice number");

            System.out.println("1.Account channel");
            System.out.println("2.Position channel");//--------->Shows wrong URL message
            System.out.println("3.Balance and position channel");//----->wrong URL message
            System.out.println("4.Position risk warning channel");
            System.out.println("5.Account greeks channel");
            System.out.println("6.Order channel");//------->Shows wrong URL message
            System.out.println("7.Place order(Market Order)");
            System.out.println("8.Place order(Limit Order)");
            System.out.println("9.Cancel order");
            System.out.println("10.Amend order");


            System.out.println("11.LOGOUT");

            int choice=sc.nextInt();

            switch(choice)
            {
                case 1:
                     itr=currencyList.iterator();
                    while(itr.hasNext()) {
                        webSocketClient.subscribePrivateDemoTradingChannel("account",itr.next(),"","","","");
                    }
                    break;

                case 2:
                    webSocketClient.subscribePrivateDemoTradingChannel("positions","","FUTURES","BTC-USD","BTC-USD-200329","");
                    break;

                case 3:
                    webSocketClient.subscribePrivateDemoTradingChannel("balance_and_position","","","","","");
                    break;

                case 4:
                     itr=instrumentTypeList.iterator();
                     while(itr.hasNext()) {

                         webSocketClient.subscribePrivateDemoTradingChannel("liquidation-warning", "", itr.next(), "", "","");
                     }
                    break;

                case 5:
                    itr=currencyList.iterator();

                    while(itr.hasNext()) {
                        webSocketClient.subscribePrivateDemoTradingChannel("account-greeks", itr.next(), "", "", "","");
                    }
                    break;

                case 6:
                    webSocketClient.subscribePrivateDemoTradChannel("","","","","","","","orders","SPOT","","","","");
                    break;


                case 7:
                    webSocketClient.subscribePrivateDemoTradChannel("1512","order","buy","OKB-USDT","isolated","market","1","","","","","","");
                    break;

                case 8:
                    webSocketClient.subscribePrivateDemoTradChannel("1513","order","buy","OKB-USDT","isolated","limit","1","","","","","50","");
                    break;

                case 9:
                    webSocketClient.subscribePrivateDemoTradChannel("1514","cancel-order","","OKB-USDT","","","","","","","653255458542678016","","");
                    break;

                case 10:
                    webSocketClient.subscribePrivateDemoTradChannel("1515","amend-order","","OKB-USDT","","","","","","","653262403093753856","","2");
                    break;

                case 11:
                    return;


                default:
                    System.out.println("Your choice is wrong!!");

            }
        }
    }//demoPrivateChannelSelection

    //****************************************************************************************************

    public void demoPrivateChannelSelection2(ArrayList<String> spreadIdList)
    {
       while(true)
       {
           System.out.println("Enter your choice number");

           System.out.println("1.Rfqs channel");
           System.out.println("2.Quotes channel");
           System.out.println("3.Structure block trades channel");
           System.out.println("4.Order channel(spread-order channel)");
           System.out.println("5.Trades channel");
           System.out.println("6.Place order"); //------>"msg":"You don't have permission to use this API endpoint"



           System.out.println("7.LOGOUT");

           int choice=sc.nextInt();

           switch(choice)
           {
               case 1:
                   webSocketClient.subscribePrivateDemoTradingChannel("rfqs","","","","","");
                   break;

               case 2:
                   webSocketClient.subscribePrivateDemoTradingChannel("quotes","","","","","");
                   break;

               case 3:
                   webSocketClient.subscribePrivateDemoTradingChannel("struc-block-trades","","","","","");
                   break;

               case 4:
                   itr=spreadIdList.iterator();
                   while(itr.hasNext()) {
                       webSocketClient.subscribePrivateDemoTradingChannel("sprd-orders", "", "", "", "", itr.next());
                   }
                   break;

               case 5:
                   itr=spreadIdList.iterator();
                   while(itr.hasNext()) {
                       webSocketClient.subscribePrivateDemoTradingChannel("sprd-trades", "", "", "", "",itr.next());
                   }
                   break;

               case 6:
                   webSocketClient.subscribedemoTradeApiChannel("1412","sprd-order","OKB-USDT_OKB-USDT-SPOT","","buy","limit","10.15","1");
                   break;





               case 7:
                   return;




               default:
                   System.out.println("Your choice is wrong!!");

           }
       }
    }

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

    public void choiceBasePrivateChannelSelection(ArrayList<String> currencyList,ArrayList<String> instrumentTypeList)
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

            System.out.println("0.deposit info channel");
            System.out.println("1.withdrawal info Channel");
            System.out.println("2.position channel");
            System.out.println("3.balance and position channel");
            System.out.println("4.order channel");
            System.out.println("5.trade channel");
            System.out.println("6.Account channel");
            System.out.println("7.Position risk warning");
            System.out.println("8.Account greeks channel");
            System.out.println("9.LOGOUT");

            int choice=sc.nextInt();

            switch(choice)
            {
                case 0:
                    webSocketClient.subscribeToPrivateChannel("deposit-info","","","");
                    break;

                case 1:
                    webSocketClient.subscribeToPrivateChannel("withdrawal-info","","","");
                    break;

                case 2:
                    webSocketClient.subcribeToPrivatePositionChannel("positions","FUTURES","BTC-USD","BTC-USD-200329");
                    break;

                case 3:
                    webSocketClient.subscribePrivateBalanceAndPosition("balance_and_position");
                    break;

                case 4:
                    webSocketClient.subscribeToPrivateChannel("sprd-orders","BTC-USDT_BTC-USDT-SWAP","","");
                    break;

                case 5:
                    webSocketClient.subscribeToPrivateChannel("sprd-trades","BTC-USDT_BTC-USDT-SWAP","","");
                    break;

                case 6:
                    itr=currencyList.iterator();
                    while(itr.hasNext()) {
                        //System.out.println("Element:"+itr.next());
                        webSocketClient.subscribeToPrivateChannel("account",itr.next(),"","");
                    }
                    break;

                case 7:
                    itr=instrumentTypeList.iterator();
                    while(itr.hasNext())
                    {
                        webSocketClient.subscribeToPrivateChannel("liquidation-warning","","",itr.next());
                    }
                    break;

                case 8:
                    itr=currencyList.iterator();
                    while(itr.hasNext())
                    {
                        webSocketClient.subscribeToPrivateChannel("account-greeks","",itr.next(),"");
                    }
                    break;

                case 9:
                    return;

                default:
                    System.out.println("Your choice is wrong!!");
                    break;

            }
        }
    }
}
