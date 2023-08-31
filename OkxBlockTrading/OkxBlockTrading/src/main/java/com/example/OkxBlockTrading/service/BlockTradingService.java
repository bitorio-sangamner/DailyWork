package com.example.OkxBlockTrading.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.blockTrading.param.CancelRfq;
import com.okex.open.api.bean.blockTrading.param.CreateQuote;
import com.okex.open.api.bean.blockTrading.param.CreateRfq;
import com.okex.open.api.bean.blockTrading.param.ExecuteQuote;
import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.blockTrading.BlockTradingAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockTradingService
{
    @Autowired
    private final APIConfiguration apiConfiguration;

    @Autowired
    private final BlockTradingAPIService blockTradingAPIService;

    public BlockTradingService(APIConfiguration apiConfiguration, BlockTradingAPIService blockTradingAPIService)
    {
        this.apiConfiguration = apiConfiguration;
        this.blockTradingAPIService = blockTradingAPIService;
    }

    public JSONObject getCounterparties()
    {
        JSONObject json=blockTradingAPIService.getCounterparties();
        return json;
    }

    public JSONObject createRfq(CreateRfq createRfq)
    {
        JSONObject json=blockTradingAPIService.createRfq(createRfq);
        return json;
    }

    public JSONObject getRfqs()
    {
        JSONObject json = blockTradingAPIService.getRfqs("","","","","","");
        return json;
    }

    public JSONObject cancleRFQ(CancelRfq cancleRfq)
    {
        JSONObject json=blockTradingAPIService.cancelRfq(cancleRfq);
        return json;
    }

    public JSONObject getPublicTrades()
    {
        JSONObject json=blockTradingAPIService.getPublicTrades("","","");
        return json;
    }

    public JSONObject getMakerInstrumentSettings()
    {
        JSONObject json=blockTradingAPIService.getMakerInstrumentSettings();
        return json;
    }

    public JSONObject resetMMPStatus() //don't have permission
    {
        JSONObject json=blockTradingAPIService.resetMMPStatus();
        return json;
    }



    public JSONObject getQuotes()
    {
        JSONObject json=blockTradingAPIService.getQuotes("","","","","","","","");
        return json;
    }

    public JSONObject executeQuote(ExecuteQuote executeQuote)
    {
        JSONObject json=blockTradingAPIService.executeQuote(executeQuote);
        return json;
    }

    public JSONObject getTrades()
    {
        JSONObject json=blockTradingAPIService.getTrades("","","","","","","","","1597026383085","1597026383085");
        return json;
    }
     public JSONObject createQuote(CreateQuote createQuote)
     {
         JSONObject json=blockTradingAPIService.createQuote(createQuote);
         return json;
     }

     public JSONObject cancelAllRfqs()
     {
         JSONObject json=blockTradingAPIService.cancelAllRfqs();
         return json;
     }
}
