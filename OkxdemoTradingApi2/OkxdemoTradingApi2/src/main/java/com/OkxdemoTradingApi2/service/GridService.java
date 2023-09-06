package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.gridTrading.param.AmendOrderAlgo;
import com.okex.open.api.bean.gridTrading.param.OrderAlgo;
import com.okex.open.api.bean.gridTrading.param.StopOrderAlgo;
import com.okex.open.api.service.gridTrading.GridTradingAPIService;
import org.springframework.stereotype.Service;

@Service
public class GridService
{
    private final GridTradingAPIService gridTradingAPIService;

    public GridService(GridTradingAPIService gridTradingAPIService)
    {
        this.gridTradingAPIService = gridTradingAPIService;
    }

    public JSONObject orderAlgo(OrderAlgo orderAlgoObject)
    {
        JSONObject json=gridTradingAPIService.orderAlgo(orderAlgoObject);
        return json;
    }

    public JSONObject amendOrderAlgo(AmendOrderAlgo amendOrderAlgoObj)
    {
        JSONObject json=gridTradingAPIService.amendOrderAlgo(amendOrderAlgoObj);
        return json;
    }

    public JSONObject stopOrderAlgo(StopOrderAlgo stopOrderAlgoObj)
    {
        JSONObject json=gridTradingAPIService.stopOrderAlgo(stopOrderAlgoObj);
        return json;
    }

    public JSONObject getGridAlgoOrderList(String algoOrdType, String algoId, String instId, String instType, String after, String before, String limit)
    {
        JSONObject json=gridTradingAPIService.getGridAlgoOrderList(algoOrdType,algoId,instId,instType,after,before,limit);
        return json;
    }

    public JSONObject getGridAlgoOrderHistory(String algoOrdType, String algoId, String instId, String instType, String after, String before, String limit)
    {
        JSONObject json=gridTradingAPIService.getGridAlgoOrderHistory(algoOrdType,algoId,instId,instType,after,before,limit);
        return json;
    }

    public JSONObject getOrdersAlgoDetails(String algoOrdType, String algoId)
    {
        JSONObject json=gridTradingAPIService.getOrdersAlgoDetails(algoOrdType,algoId);
        return json;
    }

    public JSONObject getSubOrders(String algoOrdType, String algoId, String type, String groupId, String after, String before, String limit)
    {
        JSONObject json=gridTradingAPIService.getSubOrders(algoOrdType,algoId,type,groupId,after,before,limit);
        return json;
    }

    public JSONObject getPositions(String algoOrdType, String algoId)
    {
        JSONObject json=gridTradingAPIService.getPositions(algoOrdType,algoId);
        return json;
    }
}
