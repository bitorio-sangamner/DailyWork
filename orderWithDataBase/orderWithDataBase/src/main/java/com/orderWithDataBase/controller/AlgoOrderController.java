package com.orderWithDataBase.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.AmendAlgos;
import com.okex.open.api.bean.trade.param.CancelAlgoOrder;
import com.okex.open.api.bean.trade.param.PlaceAlgoOrder;
import com.okex.open.api.exception.APIException;
import com.orderWithDataBase.entities.AlgoOrder;
import com.orderWithDataBase.service.AlgoOrderService;
import com.orderWithDataBase.service.TradeService;
import com.orderWithDataBase.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlgoOrderController {

    private static final String STATUS_KEY="status";
    private static final String MESSAGE_KEY="message";
    @Autowired
    UserOrderService userOrderService;

    AlgoOrder algoOrder;

    @Autowired
    TradeService tradeService;

    @Autowired
    AlgoOrderService algoOrderService;

    @Autowired
    JSONObject jsonObject;

    @PostMapping("/placeAlgoOrder")
    public ResponseEntity<Object> placeAlgoOrder(@RequestBody PlaceAlgoOrder algoOrderObj)
    {
        try {
              algoOrder=new AlgoOrder();

              algoOrder.setInstrumentId(algoOrderObj.getInstId());
              algoOrder.setTradeMode(algoOrderObj.getTdMode());
              algoOrder.setMarginCurrency(algoOrderObj.getCcy());
              algoOrder.setOrderSide(algoOrderObj.getSide());
              algoOrder.setPositionSide(algoOrderObj.getPosSide());
              algoOrder.setOrderType(algoOrderObj.getOrdType());
              algoOrder.setQuantity(algoOrderObj.getSz());
              algoOrder.setOrderTag(algoOrderObj.getTag());
              algoOrder.setOrderQuantityUnitSetting(algoOrderObj.getTgtCcy());
              algoOrder.setClientSuppliedAlgoID(algoOrderObj.getAlgoClOrdId());
              algoOrder.setCloseFraction(algoOrderObj.getCloseFraction());
              algoOrder.setTpTriggerPx(algoOrderObj.getTpTriggerPx());
              algoOrder.setTpOrdPx(algoOrderObj.getTpOrdPx());
              algoOrder.setTpTriggerPxType(algoOrderObj.getTpTriggerPxType());
              algoOrder.setSlTriggerPx(algoOrderObj.getSlTriggerPx());
              algoOrder.setSlOrdPx(algoOrderObj.getSlOrdPx());
              algoOrder.setSlTriggerPxType(algoOrderObj.getSlTriggerPxType());


            jsonObject=tradeService.placeAlgoOrder(algoOrderObj);
            // Get the "data" array
            JSONArray dataArray = jsonObject.getJSONArray("data");
            // Get the first object in the array
            JSONObject dataObject = dataArray.getJSONObject(0);
            // Get the value of "sMsg"
            String sMsgValue = dataObject.getString("sMsg");
            //get the value of "ordId"
            String algoOrdId=dataObject.getString("algoId");

            algoOrder.setAlgoOrderId(algoOrdId);
            algoOrderService.placeAlgoOrder(algoOrder);

            jsonObject.put("status",HttpStatus.CREATED);
            return new ResponseEntity<>(jsonObject,HttpStatus.CREATED);
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            jsonObject.put("status",HttpStatus.NOT_ACCEPTABLE);
            return new ResponseEntity<>(jsonObject,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/cancelAlgoOrder")
    public ResponseEntity<Object> cancelAlgoOrder(@RequestBody List<CancelAlgoOrder> cancelAlgoOrderList)
    {
        try {
            String msg = algoOrderService.cancelAlgoOrder(cancelAlgoOrderList);
            jsonObject = tradeService.cancelAlgoOrder(cancelAlgoOrderList);

            // Get the "data" array
            JSONArray dataArray = jsonObject.getJSONArray("data");

            // Get the first object in the array
            JSONObject dataObject = dataArray.getJSONObject(0);

            // Get the value of "sMsg"
            String sMsgValue = dataObject.getString("sMsg");

            if(msg.equals("order deleted") && !sMsgValue.equals("Order cancellation failed as the order has been filled, canceled or does not exist"))
            {
                return new ResponseEntity<>(jsonObject, HttpStatus.OK);
            }
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        jsonObject.put("status",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(jsonObject,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/amendAlgoOrder")
    public ResponseEntity<Object> amendAlgoOrder(@RequestBody AmendAlgos amendAlgosObj)
    {
        try {
            jsonObject = tradeService.amendAlgoOrder(amendAlgosObj);

            String msg = algoOrderService.amendAlgoOrder(amendAlgosObj);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
           return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/algoOrderDetails")
    public ResponseEntity<Object> getAlgoOrderDetails(@RequestBody JSONObject json)
    {
        try {
            String algoOrderId = json.getString("algoId");
            jsonObject = tradeService.getAlgoOrderDetails(algoOrderId);
            AlgoOrder algoOrderObj = algoOrderService.getAlgoOrderDetails(algoOrderId);

            if (algoOrderObj != null && (jsonObject.getString("msg") != "Order does not exist"))
            {
                return new ResponseEntity<>(jsonObject, HttpStatus.OK);
            }
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
       jsonObject.put("status",HttpStatus.NOT_FOUND);
       return new ResponseEntity<>(jsonObject,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAlgoOrderList")
    public ResponseEntity<Object> getAlgoOrderList(@RequestBody JSONObject json)
    {
        String orderType=json.getString("ordType");
        jsonObject=tradeService.getAlgoOrderList(orderType);
        List<AlgoOrder> algoOrderList=algoOrderService.getAlgoOrderList(orderType);
        return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }

    @PostMapping("/placeAlgoTriggerOrder")
    public ResponseEntity<Object> placeAlgoTriggerOrder(@RequestBody PlaceAlgoOrder algoOrderObj)
    {
        algoOrder=new AlgoOrder();

        algoOrder.setInstrumentId(algoOrderObj.getInstId());
        algoOrder.setTradeMode(algoOrderObj.getTdMode());
        algoOrder.setMarginCurrency(algoOrderObj.getCcy());
        algoOrder.setOrderSide(algoOrderObj.getSide());
        algoOrder.setPositionSide(algoOrderObj.getPosSide());
        algoOrder.setOrderType(algoOrderObj.getOrdType());
        algoOrder.setQuantity(algoOrderObj.getSz());
        algoOrder.setOrderTag(algoOrderObj.getTag());
        algoOrder.setOrderQuantityUnitSetting(algoOrderObj.getTgtCcy());
        algoOrder.setClientSuppliedAlgoID(algoOrderObj.getAlgoClOrdId());
        algoOrder.setCloseFraction(algoOrderObj.getCloseFraction());
        algoOrder.setTpTriggerPx(algoOrderObj.getTpTriggerPx());
        algoOrder.setTpOrdPx(algoOrderObj.getTpOrdPx());
        algoOrder.setTpTriggerPxType(algoOrderObj.getTpTriggerPxType());
        algoOrder.setSlTriggerPx(algoOrderObj.getSlTriggerPx());
        algoOrder.setSlOrdPx(algoOrderObj.getSlOrdPx());
        algoOrder.setSlTriggerPxType(algoOrderObj.getSlTriggerPxType());
        algoOrder.setTriggerPx(algoOrderObj.getTriggerPx());
        algoOrder.setOrderPx(algoOrderObj.getOrderPx());
        algoOrder.setTriggerPxType(algoOrderObj.getTriggerPxType());

        jsonObject=tradeService.placeAlgoTriggerOrder(algoOrderObj);

        // Get the "data" array
        JSONArray dataArray = jsonObject.getJSONArray("data");
        // Get the first object in the array
        JSONObject dataObject = dataArray.getJSONObject(0);
        // Get the value of "sMsg"
        String sMsgValue = dataObject.getString("sMsg");
        //get the value of "ordId"
        String algoOrdId=dataObject.getString("algoId");

        algoOrder.setAlgoOrderId(algoOrdId);
        algoOrderService.placeAlgoTriggerOrder(algoOrder);

        jsonObject.put("status",HttpStatus.CREATED);
        return new ResponseEntity<>(jsonObject,HttpStatus.CREATED);
    }
}
