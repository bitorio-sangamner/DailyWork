package com.orderWithDataBase.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.AmendOrder;
import com.okex.open.api.bean.trade.param.CancelOrder;
import com.okex.open.api.bean.trade.param.PlaceOrder;
import com.orderWithDataBase.entities.UserOrder;
import com.orderWithDataBase.service.TradeService;
import com.orderWithDataBase.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private static final String STATUS_KEY="status";
    private static final String MESSAGE_KEY="message";
    @Autowired
    UserOrderService userOrderService;

    UserOrder userOrder;

    @Autowired
    TradeService tradeService;

    @Autowired
    JSONObject jsonObject;

    @Autowired
    JSONObject jsonObjectNew;

    @PostMapping("/placeOrder")
    public ResponseEntity<Object> placeOrder(@RequestBody PlaceOrder order)
    {
        // Instantiate the userOrder object
        userOrder = new UserOrder();

        userOrder.setInstrumentId(order.getInstId());
        userOrder.setTradeMode(order.getTdMode());
        userOrder.setOrderSide(order.getSide());
        userOrder.setPositionSide(order.getPosSide());
        userOrder.setOrderType(order.getOrdType());
        userOrder.setQuantity(order.getSz());
        userOrder.setOrderPrice(order.getPx());
        userOrder.setTpTriggerPx(order.getTpTriggerPx());
        userOrder.setTpOrdPx(order.getTpOrdPx());
        userOrder.setSlTriggerPx(order.getSlTriggerPx());
        userOrder.setSlOrdPx(order.getSlOrdPx());

        jsonObject=tradeService.placeOrderOnOkx(order);


        // Get the "data" array
        JSONArray dataArray = jsonObject.getJSONArray("data");
        // Get the first object in the array
        JSONObject dataObject = dataArray.getJSONObject(0);
        // Get the value of "sMsg"
        String sMsgValue = dataObject.getString("sMsg");
        //get the value of "ordId"
        String ordId=dataObject.getString("ordId");

        if(!ordId.equals("")) {
            userOrder.setOrderId(ordId);
            UserOrder userOrderObj = userOrderService.saveOrder(userOrder);

            if (!sMsgValue.equals("Order placed") && userOrderObj == null) {
                dataObject.put(STATUS_KEY, HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
    }//placeOrder

    @PostMapping("/cancelOrderFromOkx")
    public ResponseEntity<Object> cancelOrderFromOkx(@RequestBody CancelOrder cancelOrderObj)
    {
        String orderId=cancelOrderObj.getOrdId();

        jsonObject=tradeService.cancelOrderFromOkx(cancelOrderObj);
        String msg=userOrderService.cancelOrder(orderId);

        // Get the "data" array
        JSONArray dataArray = jsonObject.getJSONArray("data");

        // Get the first object in the array
        JSONObject dataObject = dataArray.getJSONObject(0);

        // Get the value of "sMsg"
        String sMsgValue = dataObject.getString("sMsg");

        if(jsonObject.get("msg").equals("") && msg.equals("order deleted") && !sMsgValue.equals("Order cancellation failed as the order has been filled, canceled or does not exist"))
        {
            dataObject.put("sMsg","order deleted");
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }

        jsonObject.put(STATUS_KEY,HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(jsonObject, HttpStatus.NOT_ACCEPTABLE);

    }

    @PostMapping("/amendOrder")
    public ResponseEntity<Object> amendOrderFromOkx(@RequestBody AmendOrder amendOrderObj)
    {
        String msg="";
        jsonObject=tradeService.amendOrderFromOkx(amendOrderObj);

        JSONArray dataArray=jsonObject.getJSONArray("data");
        JSONObject jsonObj=dataArray.getJSONObject(0);
        String sMsg=jsonObj.getString("sMsg");

        if(!sMsg.equals("Your order has already been filled or canceled")) {
             msg = userOrderService.amendOrder(amendOrderObj);
        }

        System.out.println("message :"+msg);
        if(msg.equals("order amended")) {
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/orderDetails")
    public ResponseEntity<Object> getOrderDetails(@RequestBody JSONObject json)
    {
        String instrumentId=json.getString("instId");
        String orderId=json.getString("ordId");

        jsonObject=tradeService.getOrderDetails(instrumentId,orderId);
        if(jsonObject.get("msg").equals(""))
        {
            jsonObject.put(STATUS_KEY,HttpStatus.OK);
            return new ResponseEntity<>(jsonObject,HttpStatus.OK);
        }
        jsonObject.put(STATUS_KEY,HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(jsonObject, HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/updateStatusOfOrder")
    public ResponseEntity<Object> updateStatusOfOrder(@RequestBody JSONObject json)
    {
        String instrumentId=json.getString("instId");
        String orderId=json.getString("ordId");
        jsonObject=tradeService.getOrderDetails(instrumentId,orderId);

        //System.out.println("json :"+jsonObject);

        JSONArray jsonArray=jsonObject.getJSONArray("data");

        if(jsonArray.isEmpty() && jsonObject.getString("msg").equals("Order does not exist"))
        {
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            JSONObject dataObject = jsonArray.getJSONObject(0);

            String status = dataObject.getString("state");

            String message = userOrderService.updatedStatus(orderId, status);

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
