package futureTrade;

import futureTrade.channelSelection.ChoiceBaseFutureTradeChannelSelection;
import futureTrade.config.WebsocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FutureTradeApplication implements CommandLineRunner {

	@Autowired
	WebsocketConfig websocketConfig;

	@Autowired
	ChoiceBaseFutureTradeChannelSelection choiceBaseFutureTradeChannelSelection;
	public static void main(String[] args) {
		SpringApplication.run(FutureTradeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		websocketConfig.loginConnect();
		choiceBaseFutureTradeChannelSelection.choiceBaseFutureTradeChannelSelection();

	}
}
