package com.orderWithDataBase.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.PlaceOrder;
import com.orderWithDataBase.entities.UserOrder;
import com.orderWithDataBase.service.TradeService;
import com.orderWithDataBase.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

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
        userOrderService.saveOrder(userOrder);

        return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);


    }
}
