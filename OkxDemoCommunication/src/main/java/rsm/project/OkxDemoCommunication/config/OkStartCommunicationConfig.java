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
import com.okex.open.api.service.publicData.PublicDataAPIService;
import com.okex.open.api.service.publicData.impl.PublicDataAPIServiceImpl;
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
}
