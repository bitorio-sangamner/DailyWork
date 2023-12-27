package com.orderWithDataBase.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.PlaceOrder;
import com.okex.open.api.service.trade.TradeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    @Autowired
    private TradeAPIService tradeAPIService;

    @Autowired
    JSONObject jsonObject;

    public JSONObject placeOrderOnOkx(PlaceOrder orderObj)
    {
        jsonObject=tradeAPIService.placeOrder(orderObj);
        return jsonObject;
    }
}