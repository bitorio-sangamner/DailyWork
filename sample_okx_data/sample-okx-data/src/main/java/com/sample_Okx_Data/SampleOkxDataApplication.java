package com.sample_Okx_Data;

import com.sample_Okx_Data.config.PrivateWebsocketConfig;
import com.sample_Okx_Data.config.WebSocketClient;
import com.sample_Okx_Data.config.WebSocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sample_Okx_Data.constants.MyAppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@SpringBootApplication
public class SampleOkxDataApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SampleOkxDataApplication.class);
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
//		logger.info("\"You have two choices,select either 1 or 2\"");
//		logger.info("\"1.okx public channels\"");
//		logger.info("\"2.okx private channels\"");


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
			choiceBasePrivateChannelSelection();

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
	private void bookchannel() {

		bookChannelList.add(MyAppConstants.btcUsdtPair);
		bookChannelList.add(MyAppConstants.ethUsdtPair);
		bookChannelList.add(MyAppConstants.maticUsdtPair);
	}

	public void tradeChannel()
	{
		tradeChannelList.add(MyAppConstants.btcUsdtPair);
		tradeChannelList.add(MyAppConstants.ethUsdtPair);
		tradeChannelList.add(MyAppConstants.maticUsdtPair);
	}

	public void tickerChannel()
	{
      tickerChannelList.add(MyAppConstants.btcUsdtPair);
	  tickerChannelList.add(MyAppConstants.ethUsdtPair);
	  tickerChannelList.add(MyAppConstants.maticUsdtPair);
	}

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

			int choice=scanner.nextInt();

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
