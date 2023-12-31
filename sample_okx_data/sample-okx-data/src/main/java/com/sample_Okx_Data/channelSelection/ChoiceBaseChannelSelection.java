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
                    webSocketClient.subscribePrivateDemoTradChannel("1512","order","buy","OKB-USDT","spot_isolated","market","1","","","","","","");
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
           System.out.println("7.Algo orders channel");//----->Wrong URL or channel:orders-algo,instType:FUTURES,instFamily:,instId: doesn't exist
           System.out.println("8.Advance algo orders channel");//---->Wrong URL or channel:orders-algo,instType:FUTURES,instFamily:,instId: doesn't exist
           System.out.println("9.Spot grid algo orders channel");


           System.out.println("10.LOGOUT");

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
                   webSocketClient.subscribePrivateDemoAlgoTradChannel("orders-algo","FUTURES","","");
                   break;

               case 8:
                   webSocketClient.subscribePrivateDemoAlgoTradChannel("algo-advance","SPOT","","");
                   break;

               case 9:
                   webSocketClient.subscribePrivateDemoGridTradChannel("grid-orders-spot","SPOT");
                   break;


               case 10:
                   return;

               default:
                   System.out.println("Your choice is wrong!!");

           }
       }
    }

}
