package com.orderWithDataBase.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.copytrading.param.AlgoOrder;
import com.okex.open.api.bean.trade.param.*;
import com.okex.open.api.service.trade.TradeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public JSONObject cancelOrderFromOkx(CancelOrder cancelOrderObj)
    {
        jsonObject=tradeAPIService.cancelOrder(cancelOrderObj);
        return jsonObject;
    }

    public JSONObject amendOrderFromOkx(AmendOrder amendOrderObj)
    {
        jsonObject=tradeAPIService.amendOrder(amendOrderObj);
        return jsonObject;
    }

    public JSONObject getOrderDetails(String instrumentId,String orderId)
    {
        jsonObject=tradeAPIService.getOrderDetails(instrumentId,orderId,"");
        return jsonObject;
    }

    public JSONObject placeAlgoOrder(PlaceAlgoOrder algoOrderObj)
    {
        jsonObject=tradeAPIService.placeAlgoOrder(algoOrderObj);
        return jsonObject;
    }

    public JSONObject cancelAlgoOrder(List<CancelAlgoOrder> cancelAlgoOrderList)
    {
        jsonObject=tradeAPIService.cancelAlgoOrder(cancelAlgoOrderList);
        return jsonObject;
    }

    public JSONObject amendAlgoOrder(AmendAlgos amendAlgosObj)
    {
        jsonObject=tradeAPIService.amendAlgos(amendAlgosObj);
        return jsonObject;
    }

    public JSONObject getAlgoOrderDetails(String algoOrderId)
    {
        jsonObject=tradeAPIService.getAlgoOrderDetails(algoOrderId,"");
        return jsonObject;
    }

    public JSONObject getAlgoOrderList(String orderType)
    {
        jsonObject=tradeAPIService.getAlgoOrderList(orderType,"","","","","","","");
        return jsonObject;
    }
}
