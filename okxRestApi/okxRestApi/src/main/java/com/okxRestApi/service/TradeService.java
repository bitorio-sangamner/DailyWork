package com.okxRestApi.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.AmendOrder;
import com.okex.open.api.bean.trade.param.CancelOrder;
import com.okex.open.api.bean.trade.param.ClosePositions;
import com.okex.open.api.bean.trade.param.PlaceOrder;
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

    public JSONObject getOrderDetails(String instrumentId,String orderId,String clientOrderId)
    {
        jsonObject=tradeAPIService.getOrderDetails(instrumentId,orderId,clientOrderId);
       return jsonObject;
    }

    public JSONObject amendOrder(AmendOrder amendOrderObj)
    {
        jsonObject=tradeAPIService.amendOrder(amendOrderObj);
        return jsonObject;
    }

    public JSONObject cancelOrderFromOkx(CancelOrder cancelOrderObj)
    {
        jsonObject=tradeAPIService.cancelOrder(cancelOrderObj);
        return jsonObject;
    }

    public JSONObject getOrderList()
    {
        jsonObject=tradeAPIService.getOrderList("","","","","","","","","");
        return jsonObject;
    }

    public JSONObject closePositionFromOkx(ClosePositions closePositionsObj)
    {
        jsonObject=tradeAPIService.closePositions(closePositionsObj);
        return jsonObject;
    }

    public JSONObject getSpotOrderHistoryOfLast7Days(String instrumentType)
    {
        jsonObject=tradeAPIService.getOrderHistory7days(instrumentType,"","","","","","","","","","","");
        return jsonObject;
    }

    public JSONObject getFutureOrderHistoryOfLast7Days(String instrumentType)
    {
        jsonObject=tradeAPIService.getOrderHistory7days(instrumentType,"","","","","","","","","","","");
        return jsonObject;
    }

    public JSONObject getSpotOrderHistoryOfLast3Months(String instrumentType)
    {
        jsonObject=tradeAPIService.getOrderHistory3months(instrumentType,"","","","","","","","","","","");
        return jsonObject;
    }

    public JSONObject getFutureOrderHistoryOfLast3Months(String instrumentType)
    {
        jsonObject=tradeAPIService.getOrderHistory3months(instrumentType,"","","","","","","","","","","");
        return jsonObject;
    }
    public JSONObject placeMultipleOrders(List<PlaceOrder> orderList)
    {
        jsonObject=tradeAPIService.placeMultipleOrders(orderList);
        return jsonObject;
    }


}
