package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.service.marketData.MarketDataAPIService;
import org.springframework.stereotype.Service;

@Service
public class MarketDataService
{
    private final MarketDataAPIService marketDataAPIServiceObj;

    public MarketDataService(MarketDataAPIService marketDataAPIServiceObj)
    {
        this.marketDataAPIServiceObj = marketDataAPIServiceObj;
    }

    public JSONObject getTickers(String instType,String instFamily, String uly)
    {
        JSONObject json=marketDataAPIServiceObj.getTickers(instType,instFamily,uly);
        return json;
    }

    public JSONObject getTicker(String instId)
    {
        JSONObject json=marketDataAPIServiceObj.getTicker(instId);
        return json;
    }

    public JSONObject getIndexTickers(String quoteCcy, String instId)
    {
        JSONObject json=marketDataAPIServiceObj.getIndexTickers(quoteCcy,instId);
        return json;
    }

    public JSONObject getOrderBook(String instId, String sz)
    {
        JSONObject json=marketDataAPIServiceObj.getOrderBook(instId,sz);
        return json;
    }

    public JSONObject getOrderLiteBook(String instId)
    {
        JSONObject json=marketDataAPIServiceObj.getOrderLiteBook(instId);
        return json;
    }

    public JSONObject getCandlesticks(String instId, String after, String before, String bar, String limit)
    {
        JSONObject json=marketDataAPIServiceObj.getCandlesticks(instId,after,before,bar,limit);
        return json;
    }

    public JSONObject getCandlesticksHistory(String instId, String after, String before, String bar, String limit)
    {
        JSONObject json=marketDataAPIServiceObj.getCandlesticksHistory(instId,after,before,bar,limit);
        return json;
    }

    public JSONObject getIndexCandlesticks(String instId, String after, String before, String bar, String limit)
    {
        JSONObject json=marketDataAPIServiceObj.getIndexCandlesticks(instId,after,before,bar,limit);
        return json;
    }

    public JSONObject getIndexCandlesticksHistory(String instId, String after, String before, String bar, String limit)
    {
        JSONObject json=marketDataAPIServiceObj.getIndexCandlesticksHistory(instId,after,before,bar,limit);
        return json;
    }

    public JSONObject getMarkPriceCandlesticks(String instId, String after, String before, String bar, String limit)
    {
        JSONObject json=marketDataAPIServiceObj.getMarkPriceCandlesticks(instId,after,before,bar,limit);
        return json;
    }

}
