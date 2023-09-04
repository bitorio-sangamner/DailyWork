package com.OkxConvertService.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.convert.param.EstimateQuote;
import com.okex.open.api.bean.convert.param.Trade;
import com.okex.open.api.bean.copytrading.param.CloseSubposition;
import com.okex.open.api.bean.copytrading.param.SetInstruments;
import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.convert.ConvertAPIService;
import com.okex.open.api.service.copytrading.CopytradingAPIService;
import com.okex.open.api.service.earn.EarnAPIService;
import com.okex.open.api.service.funding.FundingAPIService;
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

    private final EarnAPIService earnAPIService;

    private final FundingAPIService fundingAPIService;

    public ConvertService(APIConfiguration apiConfiguration, ConvertAPIService convertAPIService, CopytradingAPIService copytradingAPIService, EarnAPIService earnAPIService, FundingAPIService fundingAPIService) {
        this.apiConfiguration = apiConfiguration;
        this.convertAPIService = convertAPIService;
        this.copytradingAPIService = copytradingAPIService;
        this.earnAPIService = earnAPIService;
        this.fundingAPIService = fundingAPIService;
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

    public JSONObject subpositionsHistory()
    {
        JSONObject json=copytradingAPIService.subpositionsHistory("","","","");
        return json;
    }

    public JSONObject getTotalProfitSharing()
    {
        JSONObject json=copytradingAPIService.getTotalProfitSharing();
        return json;
    }

    public JSONObject getUnrealizedProfitSharingDetails()
    {
        JSONObject json=copytradingAPIService.getUnrealizedProfitSharingDetails();
        return json;
    }

    public JSONObject getProfitSharingDetails()
    {
        JSONObject json=copytradingAPIService.getProfitSharingDetails("","","");
        return json;
    }

    public JSONObject setInstruments(SetInstruments setInstruments)
    {
        JSONObject json=copytradingAPIService.setInstruments(setInstruments);
        return json;
    }

    public JSONObject closeSubposition(CloseSubposition closeSubposition)
    {
        JSONObject json=copytradingAPIService.closeSubposition(closeSubposition);
        return json;
    }

   // *******************************Earn API******************************************

    public JSONObject getOffers()
    {
        JSONObject json=earnAPIService.getOffers("","","");
        return json;
    }

    //**********************************funding account*********************************

    public JSONObject getCurrenciesFromFundingAccount()
    {
        JSONObject json=fundingAPIService.getCurrencies();
        return json;
    }

}
