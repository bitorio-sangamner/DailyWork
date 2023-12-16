package com.okxRestApi.config;

import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.publicData.PublicDataAPIService;
import com.okex.open.api.service.publicData.impl.PublicDataAPIServiceImpl;
import com.okex.open.api.service.trade.TradeAPIService;
import com.okex.open.api.service.trade.impl.TradeAPIServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {


    @Bean
    public APIConfiguration okexApiConfig() {

        APIConfiguration apiConfiguration = new APIConfiguration();

        apiConfiguration.setDomain("https://www.okx.com");

        apiConfiguration.setApiKey("455577ff-5177-4177-9e27-9485836b0b14");
        apiConfiguration.setSecretKey("ADF5FF955F2A370A1C50DF85B4CB5753");
        apiConfiguration.setPassphrase("80c60758-beA16");
        apiConfiguration.setxSimulatedTrading("1");
        apiConfiguration.setPrint(true);
        return apiConfiguration;
    }

    @Bean
    public TradeAPIService TradeApiService(APIConfiguration config)
    {
       return new TradeAPIServiceImpl(config);
    }

    @Bean
    public PublicDataAPIService PublicDataAPIService(APIConfiguration config)
    {
        return new PublicDataAPIServiceImpl(config);
    }
}
