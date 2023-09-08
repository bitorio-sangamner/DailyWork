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

    public JSONObject getMarkPriceCandlesticksHistory(String instId, String after, String before, String bar, String limit)
    {
        JSONObject json=marketDataAPIServiceObj.getMarkPriceCandlesticksHistory(instId,after,before,bar,limit);
        return json;
    }

    public JSONObject getTrades(String instId, String limit)
    {
        JSONObject json=marketDataAPIServiceObj.getTrades(instId,limit);
        return json;
    }

    public JSONObject getTradesHistory(String instId, String after, String before, String limit,String type)
    {
        JSONObject json=marketDataAPIServiceObj.getTradesHistory(instId,after,before,limit,type);
        return json;
    }

    public JSONObject getTotalVolume()
    {
        JSONObject json=marketDataAPIServiceObj.getTotalVolume();
        return json;
    }

    public JSONObject getOracle()
    {
        JSONObject json=marketDataAPIServiceObj.getOracle();
        return json;
    }

    public JSONObject getExchangeRate()
    {
        JSONObject json=marketDataAPIServiceObj.getExchangeRate();
        return json;
    }

    public JSONObject getIndexComponents(String index)
    {
        JSONObject json=marketDataAPIServiceObj.getIndexComponents(index);
        return json;
    }

    public JSONObject getBlockTickers(String instType, String uly, String instFamily)
    {
        JSONObject json=marketDataAPIServiceObj.getBlockTickers(instType,uly,instFamily);
        return json;
    }

    public JSONObject getBlockTicker(String instId)
    {
        JSONObject json=marketDataAPIServiceObj.getBlockTicker(instId);
        return json;
    }

    public JSONObject getBlockTrades(String instId)
    {
        JSONObject json=marketDataAPIServiceObj.getBlockTrades(instId);
        return json;
    }

    public JSONObject getInstrumentFamilyTrades(String instFamily)
    {
        JSONObject json=marketDataAPIServiceObj.getInstrumentFamilyTrades(instFamily);
        return json;
    }


}
