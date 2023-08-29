package com.OkxdemoTradingApi2.service;

import com.OkxdemoTradingApi2.exception.ResourceNotFoundException;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.account.param.*;
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

   public JSONObject getPositions()
   {
       JSONObject json=accountAPIService.getPositions("SWAP","BTC-USD-SWAP","");
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

   public JSONObject getAccountAndPosition(String instType)
   {
       JSONObject json=accountAPIService.getAccountAndPosition(instType);
       return json;
   }

   public JSONObject getBillsDetails7Days()//ERROR
   {
       JSONObject json= accountAPIService.getBillsDetails7Days("SWAP","BTC","isolated","linear","Trade","null","1","2","100");
       return json;
   }

   public JSONObject getPositionTiers(String instType, String uly,String instFamily)
   {
       JSONObject json=accountAPIService.getPositionTiers(instType,uly,instFamily);
       return json;
   }

    public JSONObject setRiskOffsetType(IncreaseDecreaseMargin increaseDecreaseMargin)
    {
        JSONObject json=accountAPIService.setRiskOffsetType(increaseDecreaseMargin);
        return json;
    }

    /*public JSONObject setAccountLevel(AccountMode accountModeObj)
    {
        accountAPIService.setAccountLevel(accountModeObj);
    }*/

    public JSONObject activateOption()
    {
        JSONObject json=accountAPIService.activateOption();
        return json;
    }
    public JSONObject getAccountGreeks(String ccy)//------>Account mode is required
    {
        JSONObject json=accountAPIService.getAccountGreeks(ccy);
        return json;
    }

    public JSONObject setIsolatedMode(SetIsolatedMode setIsolatedMode)//----->Account mode is required
    {
        JSONObject json=accountAPIService.setIsolatedMode(setIsolatedMode);
        return json;
    }

    public JSONObject getFeeRates(String instType, String instId, String uly,String instFamily)
    {
        JSONObject json=accountAPIService.getFeeRates(instType,instId,uly,instFamily);
        return json;
    }

    public JSONObject setAutoLoan(SetAutoLoan setAutoLoan)//---->Only cross-margin accounts that cross currencies can be set to borrow coins automatically
    {
        JSONObject json=accountAPIService.setAutoLoan(setAutoLoan);

        return json;
    }


    //Mmp class not found in library
    /*public JSONObject mmpConfig(Mmp mmpConfig)
    {

    }*/

    /*public JSONObject getMmp(String instTypr)
    {

    }*/

    //bellow method not found in library----->okex-java-sdk-api-1.0.0
   /* public JSONObject getAdjustLeverageInfo(String instType, String mgnMode, String lever, String instId, String ccy, String posSide)
    {
        accountAPIService.getAdjustLeverageInfo(instType,mgnMode,lever,instId,ccy,posSide);
    }*/

   /* public JSONObject getInterestLimits()
    {
        accountAPIService.getInterestLimits()
    }*/

    public JSONObject getMaximumTradableSizeForInstrument(String instId, String tdMode, String ccy, String px,String leverage,Boolean unSpotOffset)
    {
        JSONObject json=accountAPIService.getMaximumTradableSizeForInstrument(instId,tdMode,ccy,px,leverage,unSpotOffset);
        return json;

    }
}
