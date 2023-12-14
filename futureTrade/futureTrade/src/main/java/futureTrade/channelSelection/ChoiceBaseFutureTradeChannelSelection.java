package futureTrade.channelSelection;

import futureTrade.config.WebsocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ChoiceBaseFutureTradeChannelSelection {

    @Autowired
    WebsocketClient websocketClient;
    Scanner sc=new Scanner(System.in);
    public void choiceBaseFutureTradeChannelSelection()
    {
        while(true)
        {
          System.out.println("Enter your choice");

          System.out.println("1.Place Market order");
          System.out.println("2.Place Limit order");
          System.out.println("3.Amend order");
          System.out.println("4.Logout");

          int choice=sc.nextInt();

          switch(choice)
          {
              case 1:
                  websocketClient.subscribeToTradeChannel("1213","order","buy","OKB-USDT","isolated","market","1","","long","","");
                  break;

              case 2:
                  websocketClient.subscribeToTradeChannel("1214","order","buy","OKB-USDT","isolated","limit","1","30000","long","","");
                  break;

              case 3:
                  websocketClient.subscribeToTradeChannel("1215","amend-order","","OKB-USDT","","","","","","","2");
                  break;



          }
        }
    }
}
