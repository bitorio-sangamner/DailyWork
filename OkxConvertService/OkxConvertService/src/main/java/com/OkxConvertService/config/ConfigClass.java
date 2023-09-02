package com.OkxConvertService.config;

import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.blockTrading.BlockTradingAPIService;
import com.okex.open.api.service.blockTrading.impl.BlockTradingAPIServiceImpl;
import com.okex.open.api.service.convert.ConvertAPIService;
import com.okex.open.api.service.convert.impl.ConvertAPIServiceImpl;
import com.okex.open.api.service.copytrading.CopytradingAPIService;
import com.okex.open.api.service.copytrading.impl.CopytradingAPIServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass
{
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
    public ConvertAPIService accountAPIService(APIConfiguration config)
    {
        return new ConvertAPIServiceImpl(config);
    }

    @Bean
    public CopytradingAPIService copytradingAPIService(APIConfiguration config)
    {
       return new CopytradingAPIServiceImpl(config);
    }
}
