package com.sample_Okx_Data;

import com.sample_Okx_Data.config.PrivateWebsocketConfig;
import com.sample_Okx_Data.config.WebSocketClient;
import com.sample_Okx_Data.config.WebSocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class SampleOkxDataApplication implements CommandLineRunner {

	ArrayList<String> bookChannelList =new ArrayList<>();
	ArrayList<String> tradeChannelList=new ArrayList<>();

	ArrayList<String> tickerChannelList=new ArrayList<>();

	@Autowired
	WebSocketConfig webSocketConfig;

	@Autowired
	WebSocketClient webSocketClient;

	@Autowired
	PrivateWebsocketConfig privateWebsocketConfig;


	Scanner scanner=new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SampleOkxDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        System.out.println("******************************************************************");
		System.out.println("You have two choices,select either 1 or 2");
		System.out.println("1.okx public channels");
		System.out.println("2.okx private channels");

		String choice=scanner.next();

		if(choice.equals("1"))
		{
			webSocketConfig.publicConnect();
			bookchannel();
			tradeChannel();
			tickerChannel();
			choiceBaseChannelSelection();
		}
		else if(choice.equals("2")) {
			privateWebsocketConfig.loginConnect();

			//TO-DO
		}

	}

	private void choiceBaseChannelSelection() {

		while(true)
		{
			System.out.println("Enter your choice number");

			System.out.println("0.Order Book Channel");
			System.out.println("1.Trade Channel");
			System.out.println("2.Ticker Channel");
			System.out.println("3.LOGOUT");

			int choice=scanner.nextInt();

			switch(choice)
			{
				case 0:
					Iterator<String> iterator= bookChannelList.iterator();

					while(iterator.hasNext())
					{
						String instrument=iterator.next();
						webSocketClient.subscribeBookChannel(instrument,"books");
					}

					break;

				case 1:
					Iterator<String> iterator2= tradeChannelList.iterator();

					while(iterator2.hasNext())
					{
						String instrument=iterator2.next();
						webSocketClient.subscribeTradeChannel(instrument,"trades");
					}

					break;

				case 2:
					Iterator<String> iterator3=tickerChannelList.iterator();

					while(iterator3.hasNext())
					{
						String instrument=iterator3.next();
						webSocketClient.subscribeTickerChannel(instrument,"tickers");
					}
					break;

				case 3:
					return;

				default:
					System.out.println("Your choice is wrong!!");



			}
		}
	}//choiceBaseChannelSelection method
	private void bookchannel() {

		bookChannelList.add("BTC-USDT");
		bookChannelList.add("ETH-USDT");
		bookChannelList.add("MATIC-USDT");
	}

	public void tradeChannel()
	{
		tradeChannelList.add("BTC-USDT");
		tradeChannelList.add("ETH-USDT");
		tradeChannelList.add("MATIC-USDT");
	}

	public void tickerChannel()
	{
      tickerChannelList.add("BTC-USDT");
	  tickerChannelList.add("ETH-USDT");
	  tickerChannelList.add("MATIC-USDT");
	}


}
