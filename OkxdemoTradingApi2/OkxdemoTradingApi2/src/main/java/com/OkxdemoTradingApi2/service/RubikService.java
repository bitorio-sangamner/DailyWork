package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.service.rubik.RubikAPIService;
import org.springframework.stereotype.Service;

@Service
public class RubikService
{
    private final RubikAPIService rubikAPIService;

    public RubikService(RubikAPIService rubikAPIService) {
        this.rubikAPIService = rubikAPIService;
    }

   public JSONObject getSupportCoin()
   {
       JSONObject json=rubikAPIService.getSupportCoin();
       return json;
   }

   public JSONObject getTakerVolume(String ccy,String instType,String begin,String end,String period)
   {
       JSONObject json=rubikAPIService.getTakerVolume(ccy,instType,begin,end,period);
       return json;
   }

   public JSONObject getLoanRatio(String ccy)
   {
       JSONObject json=rubikAPIService.getLoanRatio(ccy,"","","");
       return json;
   }

   public JSONObject getLongShortAccountRatio(String currencyName)
   {
       JSONObject json=rubikAPIService.getLongShortAccountRatio(currencyName,"","","");
       return json;
   }

   public JSONObject getOpenInterestVolume(String currencyName)
   {
       JSONObject json=rubikAPIService.getOpenInterestVolume(currencyName,"","","");
       return json;
   }

   public JSONObject getOptionOpenInterestVolume(String ccy)
   {
       JSONObject json=rubikAPIService.getOptionOpenInterestVolume(ccy,"");
       return json;
   }

   public JSONObject getOpenInterestVolumeRatio(String currencyName)
   {
       JSONObject json=rubikAPIService.getOpenInterestVolumeRatio(currencyName,"");
       return json;
   }

   public JSONObject getOpenInterestVolumeExpiry(String currencyName)
   {
       JSONObject json=rubikAPIService.getOpenInterestVolumeExpiry(currencyName,"");
       return json;
   }

   public JSONObject getOpenInterestVolumeStrike(String ccy,String expTime)
   {
       JSONObject json=rubikAPIService.getOpenInterestVolumeStrike(ccy,expTime,"");
       return json;
   }

   public JSONObject getTakerBlockVolume(String currencyName)
   {
       JSONObject json=rubikAPIService.getTakerBlockVolume(currencyName,"");
       return json;
   }
}
