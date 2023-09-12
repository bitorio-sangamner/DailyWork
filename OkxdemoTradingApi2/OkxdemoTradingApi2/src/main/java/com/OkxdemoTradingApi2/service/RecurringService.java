package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.recurringTrading.Recurring;
import com.okex.open.api.bean.recurringTrading.RecurringAlgoOrder;
import com.okex.open.api.service.recurring.RecurringAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecurringService
{
    @Autowired
    private final RecurringAPIService recurringAPIService;

    public RecurringService(RecurringAPIService recurringAPIService) {
        this.recurringAPIService = recurringAPIService;
    }

    public JSONObject placeOrderAlgo(RecurringAlgoOrder recurringOrderObj)
    {
        JSONObject json=recurringAPIService.placeOrderAlgo(recurringOrderObj);
        return json;
    }

    public JSONObject amendOrderAlgoFromRecurring(Recurring recurringObj)
    {
        JSONObject json=recurringAPIService.amendOrderAlgo(recurringObj);
        return json;
    }

    public JSONObject getOrderAlgoDetails(String algoId)
    {
        JSONObject json=recurringAPIService.getOrderAlgoDetails(algoId);
        return json;
    }

    public JSONObject getOrderAlgoPending()
    {
        JSONObject json=recurringAPIService.getOrderAlgoPending("","","","");
        return json;
    }

    public JSONObject getOrderAlgoHistory(String algoId)
    {
        JSONObject json=recurringAPIService.getOrderAlgoHistory(algoId,"","","");
        return json;
    }

    public JSONObject stopOrderAlgo(List<Recurring> recurringList)
    {
        JSONObject json=recurringAPIService.stopOrderAlgo(recurringList);
        return json;
    }

    public JSONObject getSubOrders(String algoId)
    {
        JSONObject json=recurringAPIService.getSubOrders(algoId,"","","","");
        return json;
    }
}
