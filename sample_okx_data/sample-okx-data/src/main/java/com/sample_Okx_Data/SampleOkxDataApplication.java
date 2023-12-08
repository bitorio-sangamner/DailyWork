package com.sample_Okx_Data;

import com.sample_Okx_Data.channelSelection.ChoiceBaseChannelSelection;
import com.sample_Okx_Data.config.*;
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
	ArrayList<String> instrumentTypeList=new ArrayList<>();
	ArrayList<String> spreadIdList=new ArrayList<>();

	@Autowired
	WebSocketConfig webSocketConfig;

	@Autowired
	PrivateWebsocketConfig privateWebsocketConfig;

	@Autowired
	DemoTradingWebsocketConfig demoTradingWebsocketConfig;

	@Autowired
	DemoTradingWebsocketConfig2 demoTradingWebsocketConfig2;

	@Autowired
	ChoiceBaseChannelSelection choiceBaseChannelSelection;



	Scanner scanner=new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SampleOkxDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		makeCuttencyList();
		makeInstrumentTypeList();
		makeSpreadIdList();

        System.out.println("******************************************************************");
		System.out.println("You have 4 choices,select any one");
		System.out.println("1.okx public channels");
		System.out.println("2.okx private channels(production side)");
		System.out.println("3.okx private demo trading channels");
		System.out.println("4.okx private demo trading channels(using other URL)");


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
			choiceBaseChannelSelection.choiceBasePrivateChannelSelection(currencyList,instrumentTypeList);
		}
		else if(choice.equals("3"))
		{
			demoTradingWebsocketConfig.loginConnect();
//			makeCuttencyList();
//			makeInstrumentTypeList();
			choiceBaseChannelSelection.demoPrivateChannelSelection(currencyList,instrumentTypeList);

		}
		else if(choice.equals("4"))
		{
			demoTradingWebsocketConfig2.loginConnect();
//			makeSpreadIdList();
			choiceBaseChannelSelection.demoPrivateChannelSelection2(spreadIdList);
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
		currencyList.add(MyAppConstants.bnbCurrency);
		currencyList.add(MyAppConstants.okbCurrency);
	}
	public void makeInstrumentTypeList()
	{
		instrumentTypeList.add(MyAppConstants.futures);
		instrumentTypeList.add(MyAppConstants.margin);
		instrumentTypeList.add(MyAppConstants.option);
		instrumentTypeList.add(MyAppConstants.swap);
		instrumentTypeList.add(MyAppConstants.any);
	}

	public void makeSpreadIdList()
	{
		spreadIdList.add(MyAppConstants.bnbUsdtSpread);
		spreadIdList.add(MyAppConstants.btcUsdtSpread);
		spreadIdList.add(MyAppConstants.ethUsdtSpread);
		spreadIdList.add(MyAppConstants.maticUsdtSpread);
		spreadIdList.add(MyAppConstants.okbUsdtSpread);
	}

}//SampleOkxDataApplication
