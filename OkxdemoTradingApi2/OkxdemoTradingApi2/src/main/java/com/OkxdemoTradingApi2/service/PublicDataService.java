package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.service.publicData.PublicDataAPIService;
import org.springframework.stereotype.Service;

@Service
public class PublicDataService
{
    private final PublicDataAPIService publicDataAPIService;

    public PublicDataService(PublicDataAPIService publicDataAPIService)
    {
        this.publicDataAPIService = publicDataAPIService;
    }

    public JSONObject getInstruments(String instType,String uly,String instFamily, String instId)
    {
        JSONObject json=publicDataAPIService.getInstruments(instType,uly,instFamily,instId);
        return json;
    }

    public JSONObject getDeliveryExerciseHistory(String instType,String uly,String instFamily,String after,String before,String limit)
    {
        JSONObject json=publicDataAPIService.getDeliveryExerciseHistory(instType,uly,instFamily,after,before,limit);
        return json;
    }

    public JSONObject getOpenInterest(String instType,String uly,String instFamily	,String instId)
    {
        JSONObject json=publicDataAPIService.getOpenInterest(instType,uly,instFamily,instId);
        return json;
    }

    public JSONObject getFundingRate(String instId)
    {
        JSONObject json=publicDataAPIService.getFundingRate(instId);
        return json;
    }

    public JSONObject getFundingRateHistory(String instId,String after,String before,String limit)
    {
        JSONObject json=publicDataAPIService.getFundingRateHistory(instId,after,before,limit);
        return json;
    }

    public JSONObject getLimitPrice(String instId)
    {
        JSONObject json=publicDataAPIService.getLimitPrice(instId);
        return json;
    }

    public JSONObject getOptionMarketData(String uly,String instFamily,String expTime)
    {
        JSONObject json=publicDataAPIService.getOptionMarketData(uly,instFamily,expTime);
        return json;
    }

    public JSONObject getEstimatedDeliveryExcercisePrice(String instId)
    {
        JSONObject json=publicDataAPIService.getEstimatedDeliveryExcercisePrice(instId);
        return json;
    }
}
