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

    public JSONObject getDiscountRateAndInterestFreeQuota(String ccy,String discountLv)
    {
        JSONObject json=publicDataAPIService.getDiscountRateAndInterestFreeQuota(ccy,discountLv);
        return json;
    }

    public JSONObject getSystemTime()
    {
        JSONObject json=publicDataAPIService.getSystemTime();
        return json;
    }

    public JSONObject getMarkPrice(String instType,String uly,String instFamily,String instId)
    {
        JSONObject json=publicDataAPIService.getMarkPrice(instType,uly,instFamily,instId);
        return json;
    }

    public JSONObject getTier(String instType, String uly, String instFamily, String instId, String tdMode, String tier)
    {
        JSONObject json=publicDataAPIService.getTier(instType,uly,instFamily,instId,tdMode,tier);
        return json;
    }

    public JSONObject getInterestRateLoanQuota()
    {
        JSONObject json=publicDataAPIService.getInterestRateLoanQuota();
        return json;
    }

    public JSONObject getUnderlying(String instType)
    {
        JSONObject json=publicDataAPIService.getUnderlying(instType);
        return json;
    }

    public JSONObject getVipInterestRateLoanQuota()
    {
        JSONObject json=publicDataAPIService.getVipInterestRateLoanQuota();
        return json;
    }

    public JSONObject getInsuranceFund(String instType,String type,String uly,String instFamily,String ccy,String before,String after,String limit)
    {
        JSONObject json=publicDataAPIService.getInsuranceFund(instType,type,uly,instFamily,ccy,before,after,limit);
        return json;
    }

    public JSONObject getConvertContractCoin(String type, String instId, String sz, String px, String unit)
    {
        JSONObject json=publicDataAPIService.getConvertContractCoin(type,instId,sz,px,unit);
        return json;
    }

    public JSONObject getOptionTrades(String instId, String instFamily, String optType)
    {
        JSONObject json=publicDataAPIService.getOptionTrades(instId,instFamily,optType);
        return json;
    }

    public JSONObject getInstrumentTickBands(String instType, String instFamily)
    {
        JSONObject json=publicDataAPIService.getInstrumentTickBands(instType,instFamily);
        return json;
    }
}
