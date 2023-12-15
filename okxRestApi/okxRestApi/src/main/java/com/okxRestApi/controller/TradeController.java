package com.okxRestApi.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.AmendOrder;
import com.okex.open.api.bean.trade.param.CancelOrder;
import com.okex.open.api.bean.trade.param.PlaceOrder;
import com.okxRestApi.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

    @Autowired
    TradeService tradeService;
    JSONObject jsonObject=new JSONObject();

    @PostMapping("/placeOrderOnOkx")
    public ResponseEntity<Object> placeOrderOnOkx(@RequestBody PlaceOrder orderObj)
    {
        jsonObject=tradeService.placeOrderOnOkx(orderObj);

        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/orderDetails")
    public ResponseEntity<Object> getOrderDetailsFromOkx(@RequestBody JSONObject json)
    {
         String instrumentId=json.getString("instId");
         String orderId=json.getString("ordId");
         String clientOrderId=json.getString("clOrdId");

         jsonObject=tradeService.getOrderDetails(instrumentId,orderId,clientOrderId);
         return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }

    @PostMapping("/amendOrder")
    public ResponseEntity<Object> amendOrderFromOkx(@RequestBody AmendOrder amendOrderObj)
    {
        jsonObject=tradeService.amendOrder(amendOrderObj);
        return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }

    @PostMapping("/cancelOrderFromOkx")
    public ResponseEntity<Object> cancelOrderFromOkx(@RequestBody CancelOrder cancelOrderObj)
    {
        jsonObject=tradeService.cancelOrderFromOkx(cancelOrderObj);
        return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }


}
