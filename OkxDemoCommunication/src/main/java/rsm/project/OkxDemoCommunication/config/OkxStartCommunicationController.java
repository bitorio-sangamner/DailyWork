package rsm.project.OkxDemoCommunication.config;

import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.account.AccountAPIService;
import com.okex.open.api.service.account.impl.AccountAPIServiceImpl;
import com.okex.open.api.service.publicData.PublicDataAPIService;
import com.okex.open.api.service.publicData.impl.PublicDataAPIServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OkxStartCommunicationController {
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
}
