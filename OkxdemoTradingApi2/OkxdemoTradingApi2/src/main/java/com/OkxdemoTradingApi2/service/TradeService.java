package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.AmendOrder;
import com.okex.open.api.bean.trade.param.CancelOrder;
import com.okex.open.api.bean.trade.param.ClosePositions;
import com.okex.open.api.bean.trade.param.PlaceOrder;
import com.okex.open.api.service.trade.TradeAPIService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    private final TradeAPIService tradeAPIService;

    public TradeService(TradeAPIService tradeAPIService) {
        this.tradeAPIService = tradeAPIService;
    }

   public JSONObject placeOrder(PlaceOrder placeOrderObj)
   {
       JSONObject json=tradeAPIService.placeOrder(placeOrderObj);
       return json;
   }

   public JSONObject placeMultipleOrders(List<PlaceOrder> placeOrdersListObj)
   {
       for(int i=0;i<placeOrdersListObj.size();i++)
       {
           System.out.println(placeOrdersListObj.get(i).getPx());
           System.out.println(placeOrdersListObj.get(i).getSlOrdPx());
           System.out.println(placeOrdersListObj.get(i).getClOrdId());
       }
       JSONObject json=tradeAPIService.placeMultipleOrders(placeOrdersListObj);
       return json;
   }

   public JSONObject cancelOrder(CancelOrder cancelOrder)
   {
       System.out.println(cancelOrder.getInstId());
       System.out.println(cancelOrder.getOrdId());

       JSONObject json=tradeAPIService.cancelOrder(cancelOrder);
       return json;
   }

   public JSONObject cancelMultipleOrders(List<CancelOrder> cancelOrdersObj)
   {
       for(int i=0;i<cancelOrdersObj.size();i++)
       {
           System.out.println(cancelOrdersObj.get(i).getInstId());
           System.out.println(cancelOrdersObj.get(i).getOrdId());
       }

       JSONObject json=tradeAPIService.cancelMultipleOrders(cancelOrdersObj);
       return json;
   }

   public JSONObject amendOrder(AmendOrder amendOrderObj)
   {
       System.out.println(amendOrderObj.getNewSz());
       System.out.println(amendOrderObj.getInstId());

       JSONObject json=tradeAPIService.amendOrder(amendOrderObj);
       return json;
   }

   public JSONObject amendMultipleOrders(List<AmendOrder> amendOrdersList)
   {
       JSONObject json=tradeAPIService.amendMultipleOrders(amendOrdersList);
       return json;
   }

   public JSONObject closePositions(ClosePositions closePositionsObj)
   {
       JSONObject json=tradeAPIService.closePositions(closePositionsObj);
       return json;
   }

   public JSONObject getOrderDetails(String instId, String ordId, String clOrdId)
   {
       JSONObject json=tradeAPIService.getOrderDetails(instId,ordId,clOrdId);
       return json;
   }

   public JSONObject getOrderList()
   {
       JSONObject json=tradeAPIService.getOrderList("","","","","","","","","");
       return json;
   }

   public JSONObject getOrderHistory7days(String instType)
   {
       JSONObject json=tradeAPIService.getOrderHistory7days(instType,"","","","","","","","","","","");
       return json;
   }

   public JSONObject getOrderHistory3months(String instType)
   {
       JSONObject json=tradeAPIService.getOrderHistory3months(instType,"","","","","","","","","","","");
       return json;
   }

   public JSONObject getTransactionDetails()
   {
       JSONObject json=tradeAPIService.getTransactionDetails("","","","","","","","","","");
       return json;
   }

   public JSONObject getTransactionDetailsForThreeMonths(String instType)
   {
       JSONObject json=tradeAPIService.getTransactionDetailsForThreeMonths(instType,"","","","","","","","","");
       return json;
   }
}
