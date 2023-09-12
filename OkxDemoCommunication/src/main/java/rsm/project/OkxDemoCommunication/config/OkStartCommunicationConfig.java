package rsm.project.OkxDemoCommunication.config;

import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.account.AccountAPIService;
import com.okex.open.api.service.account.impl.AccountAPIServiceImpl;
import com.okex.open.api.service.blockTrading.BlockTradingAPIService;
import com.okex.open.api.service.blockTrading.impl.BlockTradingAPIServiceImpl;
import com.okex.open.api.service.broker.BrokerAPIService;
import com.okex.open.api.service.broker.impl.BrokerAPIServiceImpl;
import com.okex.open.api.service.convert.ConvertAPIService;
import com.okex.open.api.service.convert.impl.ConvertAPIServiceImpl;
import com.okex.open.api.service.copytrading.CopytradingAPIService;
import com.okex.open.api.service.copytrading.impl.CopytradingAPIServiceImpl;
import com.okex.open.api.service.earn.EarnAPIService;
import com.okex.open.api.service.earn.impl.EarnAPIServiceImpl;
import com.okex.open.api.service.finance.FinanceAPIService;
import com.okex.open.api.service.finance.impl.FinanceAPIServiceImpl;
import com.okex.open.api.service.funding.FundingAPIService;
import com.okex.open.api.service.funding.impl.FundingAPIServiceImpl;
import com.okex.open.api.service.gridTrading.GridTradingAPIService;
import com.okex.open.api.service.gridTrading.impl.GridTradingAPIServiceImpl;
import com.okex.open.api.service.marketData.MarketDataAPIService;
import com.okex.open.api.service.marketData.impl.MarketDataAPIServiceImpl;
import com.okex.open.api.service.publicData.PublicDataAPIService;
import com.okex.open.api.service.publicData.impl.PublicDataAPIServiceImpl;
import com.okex.open.api.service.recurring.RecurringAPIService;
import com.okex.open.api.service.recurring.impl.RecurringAPIServiceImpl;
import com.okex.open.api.service.rubik.RubikAPIService;
import com.okex.open.api.service.rubik.impl.RubikAPIServiceImpl;
import com.okex.open.api.service.status.StatusDataAPIService;
import com.okex.open.api.service.status.impl.StatusDataAPIServiceImpl;
import com.okex.open.api.service.subAccount.SubAccountAPIService;
import com.okex.open.api.service.subAccount.impl.SubAccountAPIServiceImpl;
import com.okex.open.api.service.trade.TradeAPIService;
import com.okex.open.api.service.trade.impl.TradeAPIServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OkStartCommunicationConfig {
    @Bean
    public APIConfiguration okexApiConfig() {
        APIConfiguration config = new APIConfiguration();
        config.setDomain("https://www.okx.com/");
        config.setApiKey("414ebd7d-73e0-4a9e-8aee-511c35eaca35");
        config.setSecretKey("03BD391BA3B81DEACB7E986ADB011E25");
        config.setPassphrase("Test@1234");
        config.setxSimulatedTrading("1");
        config.setPrint(true);
        return config;
    }

    @Bean
    public PublicDataAPIService publicDataAPIService(APIConfiguration config) {
        return new PublicDataAPIServiceImpl(config);
    }

    @Bean
    public AccountAPIService accountAPIService(APIConfiguration config) {
        return new AccountAPIServiceImpl(config);
    }

    @Bean
    public BlockTradingAPIService blockTradingAPIService(APIConfiguration config) { return new BlockTradingAPIServiceImpl(config);}

    @Bean
    public BrokerAPIService brokerAPIService(APIConfiguration config) {
        return new BrokerAPIServiceImpl(config);
    }

    @Bean
    public ConvertAPIService convertAPIService(APIConfiguration config) {return new ConvertAPIServiceImpl(config);}

    @Bean
    public CopytradingAPIService copytradingAPIService(APIConfiguration config) { return new CopytradingAPIServiceImpl(config);}

    @Bean
    public EarnAPIService earnAPIService(APIConfiguration config) { return new EarnAPIServiceImpl(config);}

    @Bean
    public FinanceAPIService financeAPIService(APIConfiguration config) { return new FinanceAPIServiceImpl(config);}

    @Bean
    public FundingAPIService fundingAPIService(APIConfiguration config) { return new FundingAPIServiceImpl(config);}

    @Bean
    public GridTradingAPIService gridTradingAPIService(APIConfiguration config) { return new GridTradingAPIServiceImpl(config);}

    @Bean
    public MarketDataAPIService marketDataAPIService(APIConfiguration config) { return new MarketDataAPIServiceImpl(config);}

    @Bean
    public RecurringAPIService recurringAPIService(APIConfiguration config) { return new RecurringAPIServiceImpl(config);}

    @Bean
    public RubikAPIService rubikAPIService(APIConfiguration config) { return new RubikAPIServiceImpl(config);}

    @Bean
    public StatusDataAPIService statusDataAPIService(APIConfiguration config) { return new StatusDataAPIServiceImpl(config);}

    @Bean
    public SubAccountAPIService subAccountAPIService(APIConfiguration config) { return new SubAccountAPIServiceImpl(config);}

    @Bean
    public TradeAPIService tradeAPIService(APIConfiguration config) { return new TradeAPIServiceImpl(config);}
}
