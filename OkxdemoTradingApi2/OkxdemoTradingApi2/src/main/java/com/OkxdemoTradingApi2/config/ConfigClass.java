package com.OkxdemoTradingApi2.config;

import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.account.AccountAPIService;
import com.okex.open.api.service.account.impl.AccountAPIServiceImpl;
import com.okex.open.api.service.earn.EarnAPIService;
import com.okex.open.api.service.earn.impl.EarnAPIServiceImpl;
import com.okex.open.api.service.funding.FundingAPIService;
import com.okex.open.api.service.funding.impl.FundingAPIServiceImpl;
import com.okex.open.api.service.gridTrading.GridTradingAPIService;
import com.okex.open.api.service.gridTrading.impl.GridTradingAPIServiceImpl;
import com.okex.open.api.service.marketData.MarketDataAPIService;
import com.okex.open.api.service.marketData.impl.MarketDataAPIServiceImpl;
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
        apiConfiguration.setApiKey("3cdc01cc-9f1a-4238-8cd1-93f4f807d696");
        apiConfiguration.setSecretKey("5C8F733918E547FD6813B71A9234AAF1");
        apiConfiguration.setPassphrase("75c60758-beB16");
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

    @Bean
    public EarnAPIService earnAPIService(APIConfiguration config)
    {
        return new EarnAPIServiceImpl(config);
    }

    @Bean
    public FundingAPIService fundingAPIService(APIConfiguration config)
    {
        return new FundingAPIServiceImpl(config);
    }

    @Bean
    public GridTradingAPIService gridTradingAPIService(APIConfiguration config)
    {
       return new GridTradingAPIServiceImpl(config);

    }

    @Bean
    public MarketDataAPIService MarketDataAPIService(APIConfiguration config)
    {
       return new MarketDataAPIServiceImpl(config);
    }

}
