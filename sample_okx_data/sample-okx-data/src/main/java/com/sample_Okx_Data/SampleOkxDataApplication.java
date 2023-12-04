package com.sample_Okx_Data;

import com.sample_Okx_Data.channelSelection.ChoiceBaseChannelSelection;
import com.sample_Okx_Data.config.DemoTradingWebsocketConfig;
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
	ArrayList<String> currencyList=new ArrayList<>();

	@Autowired
	WebSocketConfig webSocketConfig;

	@Autowired
	WebSocketClient webSocketClient;

	@Autowired
	PrivateWebsocketConfig privateWebsocketConfig;

	@Autowired
	DemoTradingWebsocketConfig demoTradingWebsocketConfig;

	@Autowired
	ChoiceBaseChannelSelection choiceBaseChannelSelection;



	Scanner scanner=new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SampleOkxDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        System.out.println("******************************************************************");
		System.out.println("You have 3 choices,select either 1 or 2");
		System.out.println("1.okx public channels");
		System.out.println("2.okx private channels");
		System.out.println("3.okx private demo trading channels");


		String choice=scanner.next();

		if(choice.equals("1"))
		{
			webSocketConfig.publicConnect();
			bookchannel();
			tradeChannel();
			tickerChannel();

			choiceBaseChannelSelection.choiceBasePublicChannelSelection(bookChannelList,tradeChannelList,tickerChannelList);
		}
		else if(choice.equals("2")) {
			privateWebsocketConfig.loginConnect();
			choiceBaseChannelSelection.choiceBasePrivateChannelSelection();
		}
		else if(choice.equals("3"))
		{
			demoTradingWebsocketConfig.loginConnect();
			makeCuttencyList();
			choiceBaseChannelSelection.demoPrivateChannelSelection(currencyList);

		}

	}

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

	public void makeCuttencyList()
	{
		currencyList.add(MyAppConstants.btcCurrency);
		currencyList.add(MyAppConstants.ethCurrency);
		currencyList.add(MyAppConstants.maticCurrency);
	}

}//SampleOkxDataApplication
