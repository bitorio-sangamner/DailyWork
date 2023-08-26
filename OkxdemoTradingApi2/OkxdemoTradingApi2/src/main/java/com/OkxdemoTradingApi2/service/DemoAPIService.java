package com.OkxdemoTradingApi2.service;

import com.OkxdemoTradingApi2.exception.ResourceNotFoundException;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.account.param.SetLeverage;
import com.okex.open.api.bean.account.param.SetPositionMode;
import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.account.AccountAPIService;
import com.okex.open.api.service.publicData.PublicDataAPIService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class DemoAPIService<JSON> {

    private final APIConfiguration apiConfiguration;
    private final CloseableHttpClient httpClient;

    //private final PublicDataAPIServiceImpl publicDataAPIServiceImpl;
    @Autowired
    private final PublicDataAPIService publicDataAPIService;

    @Autowired
    private final AccountAPIService accountAPIService;

    public DemoAPIService(APIConfiguration apiConfiguration, CloseableHttpClient httpClient, PublicDataAPIService publicDataAPIService, AccountAPIService accountAPIService) {
        this.apiConfiguration = apiConfiguration;
        this.httpClient = httpClient;
        this.publicDataAPIService = publicDataAPIService;
        this.accountAPIService = accountAPIService;
    }




   public JSONObject callDemoAPI()
   {

       publicDataAPIService.getSystemTime();
       System.out.println(publicDataAPIService.getSystemTime());
       return publicDataAPIService.getSystemTime();



   }

   public JSONObject getInstrument()
   {
       publicDataAPIService.getInstruments("SWAP",null,null,null);
       System.out.println(publicDataAPIService.getInstruments("SWAP",null,null,null));
       return publicDataAPIService.getInstruments("SWAP",null,null,null);

   }

   public JSONObject getAccountDetails()
   {
       accountAPIService.getAccountConfiguration();
       return accountAPIService.getAccountConfiguration();
   }

   public JSONObject getAmount(String currency)
   {
       accountAPIService.getBalance(currency);
       System.out.println(accountAPIService.getBalance(currency));
       return accountAPIService.getBalance(currency);
   }

   public JSONObject setPosionMode(SetPositionMode setPositionMode)
   {
       JSONObject json=accountAPIService.setPositionMode(setPositionMode);
       return json;
   }

   public JSONObject setLeverage(SetLeverage setLeverage)
   {
       JSONObject json=accountAPIService.setLeverage(setLeverage);
       return json;
   }

   public JSONObject getPositions(String instType, String instId,String posId)
   {
       JSONObject json=accountAPIService.getPositions(instType,instId,posId);
       return json;
   }

   public JSONObject getInterestRate(String ccy)
   {
       JSONObject json=accountAPIService.getInterestRate(ccy);
       if(json.isEmpty())
       {
           throw new ResourceNotFoundException("Interest Rate with given currency is not found!!");
       }
       return json;
   }

   public JSONObject getLeverage(String instId, String mgnMode)
   {
       JSONObject json=accountAPIService.getLeverage(instId,mgnMode);
       if(json.isEmpty())
       {
         throw new ResourceNotFoundException("leverage with given instId and mgnMode is not found!!");
       }
       return json;
   }
}
