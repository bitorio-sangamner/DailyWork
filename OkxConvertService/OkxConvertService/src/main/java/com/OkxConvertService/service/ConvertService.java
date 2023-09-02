package com.OkxConvertService.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.convert.param.EstimateQuote;
import com.okex.open.api.bean.convert.param.Trade;
import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.convert.ConvertAPIService;
import com.okex.open.api.service.copytrading.CopytradingAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertService
{
    @Autowired
    private final APIConfiguration apiConfiguration;

    @Autowired
    private final ConvertAPIService convertAPIService;

    @Autowired
    private final CopytradingAPIService copytradingAPIService;

    public ConvertService(APIConfiguration apiConfiguration, ConvertAPIService convertAPIService, CopytradingAPIService copytradingAPIService) {
        this.apiConfiguration = apiConfiguration;
        this.convertAPIService = convertAPIService;
        this.copytradingAPIService = copytradingAPIService;
    }

    public JSONObject getCurrencies()
    {
        JSONObject json=convertAPIService.getCurrencies();
        return json;
    }

    public JSONObject getCurrencyPair(String fromCcy,String toCcy)
    {
        JSONObject json=convertAPIService.getCurrencyPair(fromCcy,toCcy);
        return json;
    }

    public JSONObject estimateQuote(EstimateQuote estimateQuote)
    {
        JSONObject json=convertAPIService.estimateQuote(estimateQuote);
        return json;
    }

    public JSONObject trade(Trade trade)
    {
        JSONObject json=convertAPIService.trade(trade);
        return json;
    }

    public JSONObject getHistory()
    {
        JSONObject json=convertAPIService.getHistory("","","","");
        return json;
    }

    public JSONObject currentSubpositions(String instId)
    {
        JSONObject json=copytradingAPIService.currentSubpositions(instId);
        return json;
    }

    public JSONObject getInstruments()
    {
        JSONObject json=copytradingAPIService.getInstruments();
        return json;
    }
}
