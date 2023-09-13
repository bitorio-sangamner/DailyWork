package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.*;
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

   public JSONObject placeAlgoOrder(PlaceAlgoOrder placeAlgoOrderObj)
   {
       JSONObject json=tradeAPIService.placeAlgoOrder(placeAlgoOrderObj);
       return json;
   }

   public JSONObject getAlgoOrderList(String algoId, String instType, String instId, String ordType,String algoClOrdId, String after, String before, String limit)
   {
       JSONObject json=tradeAPIService.getAlgoOrderList(algoId,instType,instId,ordType,algoClOrdId,after,before,limit);
       return json;
   }

   public JSONObject cancelAlgoOrder(List<CancelAlgoOrder> cancelAlgoOrderList)
   {
       for(int i=0;i<cancelAlgoOrderList.size();i++)
       {
           System.out.println("AlgoId : "+cancelAlgoOrderList.get(i).getAlgoId());
           System.out.println("instId : "+cancelAlgoOrderList.get(i).getInstId());
       }
       JSONObject json=tradeAPIService.cancelAlgoOrder(cancelAlgoOrderList);
       return json;
   }

   public JSONObject cancelAdvanceAlgoOrders(List<CancelAlgoOrder> cancelAlgoOrderList)
   {
       JSONObject json=tradeAPIService.cancelAdvanceAlgoOrders(cancelAlgoOrderList);
       return json;
   }

   public JSONObject getAlgoOrderHistory(String state, String algoId, String instType, String instId, String ordType, String clOrdId,String after, String before, String limit)
   {
       JSONObject json=tradeAPIService.getAlgoOrderHistory(state,algoId,instType,instId,ordType,clOrdId,after,before,limit);
        return json;
   }

   public JSONObject getEasyConvertCurrencyList()
   {
       JSONObject json=tradeAPIService.getEasyConvertCurrencyList();
       return json;
   }

   public JSONObject placeEasyConvert(EasyConvert easyConvertObj)
   {
       JSONObject json=tradeAPIService.placeEasyConvert(easyConvertObj);
       return json;
   }

   public JSONObject getEasyConvertHistory()
   {
       JSONObject json=tradeAPIService.getEasyConvertHistory("","","");
       return json;
   }

   public JSONObject getOneClickRepayCurrencyList()
   {
       JSONObject json=tradeAPIService.getOneClickRepayCurrencyList("");
       return json;
   }

   public JSONObject oneClickRepay(OneClickRepay oneClickRepayObj)
   {
       JSONObject json=tradeAPIService.oneClickRepay(oneClickRepayObj);
       return json;
   }

   public JSONObject getOneClickRepayHistory()
   {
       JSONObject json=tradeAPIService.getOneClickRepayHistory("","","");
       return json;
   }

   public JSONObject getAlgoOrderDetails(String algoId, String algoClOrdId)
   {
       JSONObject json=tradeAPIService.getAlgoOrderDetails(algoId,algoClOrdId);
       return json;
   }

   public JSONObject amendAlgos(AmendAlgos amendAlgosObj)
   {
       JSONObject json=tradeAPIService.amendAlgos(amendAlgosObj);
       return json;
   }




}
