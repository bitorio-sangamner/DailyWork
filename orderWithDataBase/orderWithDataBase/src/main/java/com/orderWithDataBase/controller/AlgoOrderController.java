package com.orderWithDataBase.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.AmendAlgos;
import com.okex.open.api.bean.trade.param.CancelAlgoOrder;
import com.okex.open.api.bean.trade.param.PlaceAlgoOrder;
import com.okex.open.api.exception.APIException;
//import com.orderWithDataBase.entities.AlgoOrder;
import com.orderWithDataBase.entities.UserOrder;
import com.orderWithDataBase.service.AlgoOrderService;
import com.orderWithDataBase.service.TradeService;
import com.orderWithDataBase.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlgoOrderController {

    private static final String STATUS_KEY="status";
    private static final String MESSAGE_KEY="message";
    @Autowired
    UserOrderService userOrderService;

    UserOrder userAlgoOrder;

    @Autowired
    TradeService tradeService;

    @Autowired
    AlgoOrderService algoOrderService;

    @Autowired
    JSONObject jsonObject;

    @Autowired
    JSONObject jsonObjectNew;

    @PostMapping("/placeAlgoOrder")
    public ResponseEntity<Object> placeAlgoOrder(@RequestBody PlaceAlgoOrder algoOrderObj)
    {
        try {
              userAlgoOrder =new UserOrder();

              userAlgoOrder.setInstrumentId(algoOrderObj.getInstId());
              userAlgoOrder.setTradeMode(algoOrderObj.getTdMode());
              userAlgoOrder.setMarginCurrency(algoOrderObj.getCcy());
              userAlgoOrder.setOrderSide(algoOrderObj.getSide());
              userAlgoOrder.setPositionSide(algoOrderObj.getPosSide());
              userAlgoOrder.setOrderType(algoOrderObj.getOrdType());
              userAlgoOrder.setQuantity(algoOrderObj.getSz());
              userAlgoOrder.setOrderTag(algoOrderObj.getTag());
              userAlgoOrder.setOrderQuantityUnitSetting(algoOrderObj.getTgtCcy());
              userAlgoOrder.setClientSuppliedAlgoID(algoOrderObj.getAlgoClOrdId());
              userAlgoOrder.setCloseFraction(algoOrderObj.getCloseFraction());
              userAlgoOrder.setTpTriggerPx(algoOrderObj.getTpTriggerPx());
              userAlgoOrder.setTpOrdPx(algoOrderObj.getTpOrdPx());
              userAlgoOrder.setTpTriggerPxType(algoOrderObj.getTpTriggerPxType());
              userAlgoOrder.setSlTriggerPx(algoOrderObj.getSlTriggerPx());
              userAlgoOrder.setSlOrdPx(algoOrderObj.getSlOrdPx());
              userAlgoOrder.setSlTriggerPxType(algoOrderObj.getSlTriggerPxType());


            jsonObject=tradeService.placeAlgoOrder(algoOrderObj);
            // Get the "data" array
            JSONArray dataArray = jsonObject.getJSONArray("data");
            // Get the first object in the array
            JSONObject dataObject = dataArray.getJSONObject(0);
            // Get the value of "sMsg"
            String sMsgValue = dataObject.getString("sMsg");
            //get the value of "ordId"
            String algoOrdId=dataObject.getString("algoId");

            userAlgoOrder.setAlgoOrderId(algoOrdId);
            algoOrderService.placeAlgoOrder(userAlgoOrder);

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
                dataObject.put("sMsg","order deleted");
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
            UserOrder algoOrderObj = algoOrderService.getAlgoOrderDetails(algoOrderId);

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
        jsonObjectNew.put("message :","order not found either on okx or in local database or not found at both side");
        jsonObjectNew.put("status",HttpStatus.NOT_FOUND);
       return new ResponseEntity<>(jsonObjectNew,HttpStatus.NOT_FOUND);
    }


    @GetMapping("/getAlgoOrderList")
    public ResponseEntity<Object> getAlgoOrderList(@RequestBody JSONObject json)
    {
        String orderType=json.getString("ordType");
        String algoOrderId=json.getString("algoOrderId");
        jsonObject=tradeService.getAlgoOrderList(orderType,algoOrderId);

        // Get the "data" array
        JSONArray dataArray = jsonObject.getJSONArray("data");

        List<UserOrder> algoOrderList=algoOrderService.getAlgoOrderList(orderType);
        System.out.println("List :"+algoOrderList);
        if(!algoOrderList.isEmpty() && dataArray!=null) {
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        jsonObjectNew.put("message","order not found either on okx or in local database or not found at both side");
        jsonObjectNew.put("status",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(jsonObjectNew,HttpStatus.NOT_FOUND);
    }


    @GetMapping("/getAlgoOrderHistory")
    public ResponseEntity<Object> getAlgoOrderHistory(@RequestBody JSONObject json)
    {
        String orderType=json.getString("ordType");
        String state=json.getString("state");
        //String algoOrderId=json.getString("algoOrderId");

        System.out.println("order type :"+orderType);
        System.out.println("state :"+state);
        //System.out.println("algo order id :"+algoOrderId);

        jsonObject=tradeService.getAlgoOrderHistory(orderType,state);
        return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }

    @PostMapping("/placeAlgoTriggerOrder")
    public ResponseEntity<Object> placeAlgoTriggerOrder(@RequestBody PlaceAlgoOrder algoOrderObj)
    {
        userAlgoOrder =new UserOrder();

        userAlgoOrder.setInstrumentId(algoOrderObj.getInstId());
        userAlgoOrder.setTradeMode(algoOrderObj.getTdMode());
        userAlgoOrder.setMarginCurrency(algoOrderObj.getCcy());
        userAlgoOrder.setOrderSide(algoOrderObj.getSide());
        userAlgoOrder.setPositionSide(algoOrderObj.getPosSide());
        userAlgoOrder.setOrderType(algoOrderObj.getOrdType());
        userAlgoOrder.setQuantity(algoOrderObj.getSz());
        userAlgoOrder.setOrderTag(algoOrderObj.getTag());
        userAlgoOrder.setOrderQuantityUnitSetting(algoOrderObj.getTgtCcy());
        userAlgoOrder.setClientSuppliedAlgoID(algoOrderObj.getAlgoClOrdId());
        userAlgoOrder.setCloseFraction(algoOrderObj.getCloseFraction());
        userAlgoOrder.setTpTriggerPx(algoOrderObj.getTpTriggerPx());
        userAlgoOrder.setTpOrdPx(algoOrderObj.getTpOrdPx());
        userAlgoOrder.setTpTriggerPxType(algoOrderObj.getTpTriggerPxType());
        userAlgoOrder.setSlTriggerPx(algoOrderObj.getSlTriggerPx());
        userAlgoOrder.setSlOrdPx(algoOrderObj.getSlOrdPx());
        userAlgoOrder.setSlTriggerPxType(algoOrderObj.getSlTriggerPxType());
        userAlgoOrder.setTriggerPx(algoOrderObj.getTriggerPx());
        userAlgoOrder.setOrderPx(algoOrderObj.getOrderPx());
        userAlgoOrder.setTriggerPxType(algoOrderObj.getTriggerPxType());

        jsonObject=tradeService.placeAlgoTriggerOrder(algoOrderObj);

        // Get the "data" array
        JSONArray dataArray = jsonObject.getJSONArray("data");
        // Get the first object in the array
        JSONObject dataObject = dataArray.getJSONObject(0);
        // Get the value of "sMsg"
        String sMsgValue = dataObject.getString("sMsg");
        //get the value of "ordId"
        String algoOrdId=dataObject.getString("algoId");

        userAlgoOrder.setAlgoOrderId(algoOrdId);
        algoOrderService.placeAlgoTriggerOrder(userAlgoOrder);

        jsonObject.put("status",HttpStatus.CREATED);
        return new ResponseEntity<>(jsonObject,HttpStatus.CREATED);
    }

    @PutMapping("/updateStatusOfAlgoOrder")
    public ResponseEntity<Object> updateStatusOfAlgoOrder(@RequestBody JSONObject json)
    {
        String instrumentId=json.getString("instId");
        String algoOrderId=json.getString("ordId");
        jsonObject=tradeService.getAlgoOrderDetails(algoOrderId);

        JSONArray jsonArray=jsonObject.getJSONArray("data");

        if(jsonObject.getString("msg").equals("Order does not exist") && jsonArray.isEmpty())
        {
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        else {
            JSONObject dataObject = jsonArray.getJSONObject(0);

            String status = dataObject.getString("state");

            String message = algoOrderService.updateStatusOfOrder(algoOrderId, status);

            if (message.equals("Status updated")) {
                jsonObjectNew.put("message", message);
                jsonObjectNew.put("status", HttpStatus.OK);
                return new ResponseEntity<>(jsonObjectNew, HttpStatus.OK);
            } else {
                jsonObjectNew.put("message", message);
                jsonObjectNew.put("status", HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(jsonObjectNew, HttpStatus.NOT_FOUND);
            }
        }
    }
}
