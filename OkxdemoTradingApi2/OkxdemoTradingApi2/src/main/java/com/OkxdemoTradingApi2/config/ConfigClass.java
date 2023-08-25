package com.OkxdemoTradingApi2.config;

import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.account.AccountAPIService;
import com.okex.open.api.service.account.impl.AccountAPIServiceImpl;
import com.okex.open.api.service.publicData.PublicDataAPIService;
import com.okex.open.api.service.publicData.impl.PublicDataAPIServiceImpl;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigClass {

   // @Value("${okx.api.key}")
    private String apiKey;

   // @Value("${okx.secret.key}")
    private String apiSecret;

   // @Value("${okx.pass.phrase}")
    private String baseUrl;

    @Bean
    public APIConfiguration okexApiConfig() {
        APIConfiguration apiConfiguration = new APIConfiguration();
        apiConfiguration.setDomain("https://www.okx.com");
//        apiConfiguration.setApiKey("75c60758-be16-4acc-8f2d-66403e53072c");
//        apiConfiguration.setSecretKey("8DF095FE9C662F787A60F3133A06414C");
//        apiConfiguration.setPassphrase("19205A%9980");
        apiConfiguration.setApiKey("d5fda288-b395-4289-b288-d9e915e82e59");
        apiConfiguration.setSecretKey("B59FFC134E2A1E33559E650B3C0DFA3E");
        apiConfiguration.setPassphrase("75c60758-beA16");
        apiConfiguration.setxSimulatedTrading("1");
        apiConfiguration.setPrint(true);
        return apiConfiguration;
    }

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.createDefault();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PublicDataAPIService publicDataAPIService(APIConfiguration config) {
        return new PublicDataAPIServiceImpl(config);
    }

    @Bean
    public AccountAPIService accountAPIService(APIConfiguration config)
    {
        return new AccountAPIServiceImpl(config);
    }

  /*@Bean
    public PublicDataAPIServiceImpl publicDataAPIServiceImpl(APIConfiguration config) {
        return new PublicDataAPIServiceImpl(config);
    }*/
}
