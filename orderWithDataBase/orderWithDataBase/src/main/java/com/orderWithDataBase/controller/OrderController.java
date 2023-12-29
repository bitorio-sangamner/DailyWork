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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

        userOrder.setOrderId(ordId);
        UserOrder userOrderObj= userOrderService.saveOrder(userOrder);

        if(!sMsgValue.equals("Order placed") && userOrderObj==null)
        {
            dataObject.put(STATUS_KEY,HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(jsonObject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
    }//placeOrder

    @PostMapping("/cancelOrderFromOkx")
    public ResponseEntity<Object> cancelOrderFromOkx(@RequestBody CancelOrder cancelOrderObj)
    {
        String orderId=cancelOrderObj.getOrdId();

        jsonObject=tradeService.cancelOrderFromOkx(cancelOrderObj);
        String msg=userOrderService.cancelOrder(orderId);
        if(jsonObject.get("msg").equals("") && msg.equals("order deleted")) {
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }

        jsonObject.put(STATUS_KEY,HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(jsonObject, HttpStatus.NOT_ACCEPTABLE);

    }

    @PostMapping("/amendOrder")
    public ResponseEntity<Object> amendOrderFromOkx(@RequestBody AmendOrder amendOrderObj)
    {
        jsonObject=tradeService.amendOrderFromOkx(amendOrderObj);
        String msg=userOrderService.amendOrder(amendOrderObj);
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
}
