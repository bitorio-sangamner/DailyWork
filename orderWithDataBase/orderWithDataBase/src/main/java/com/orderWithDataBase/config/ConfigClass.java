package com.orderWithDataBase.config;



import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.gridTrading.GridTradingAPIService;
import com.okex.open.api.service.gridTrading.impl.GridTradingAPIServiceImpl;
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
   public GridTradingAPIService GridApiService(APIConfiguration config)
   {
      return new GridTradingAPIServiceImpl(config);
   }

    @Bean
    public JSONObject jsonObject() {
        return new JSONObject();
    }
}
