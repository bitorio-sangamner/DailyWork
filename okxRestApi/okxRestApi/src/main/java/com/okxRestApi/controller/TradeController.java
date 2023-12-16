package com.okxRestApi.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.AmendOrder;
import com.okex.open.api.bean.trade.param.CancelOrder;
import com.okex.open.api.bean.trade.param.PlaceOrder;
import com.okex.open.api.exception.APIException;
import com.okxRestApi.service.PublicDataService;
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

    @Autowired
    PublicDataService publicDataService;
    JSONObject jsonObject=new JSONObject();

    @GetMapping("/getInstruments")
    public ResponseEntity<Object> getInstruments()
    {
        jsonObject=publicDataService.getInstrumentData();
        return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }

    @PostMapping("/placeOrderOnOkx")
    public ResponseEntity<Object> placeOrderOnOkx(@RequestBody PlaceOrder orderObj)
    {
        try {
            jsonObject = tradeService.placeOrderOnOkx(orderObj);

            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {

            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orderDetails")
    public ResponseEntity<Object> getOrderDetailsFromOkx(@RequestBody JSONObject json)
    {
        try {
            String instrumentId = json.getString("instId");
            String orderId = json.getString("ordId");
            String clientOrderId = json.getString("clOrdId");

            jsonObject = tradeService.getOrderDetails(instrumentId, orderId, clientOrderId);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {

            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/amendOrder")
    public ResponseEntity<Object> amendOrderFromOkx(@RequestBody AmendOrder amendOrderObj)
    {
        try {
            jsonObject = tradeService.amendOrder(amendOrderObj);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cancelOrderFromOkx")
    public ResponseEntity<Object> cancelOrderFromOkx(@RequestBody CancelOrder cancelOrderObj)
    {
        try {
            jsonObject = tradeService.cancelOrderFromOkx(cancelOrderObj);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOrderList")
    public ResponseEntity<Object> getOrderListFromOkx()
    {
        try {
            jsonObject = tradeService.getOrderList();
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getSpotOrderHistoryLast7Days")
    public ResponseEntity<Object> getSpotOrderHistoryOfLast7Day()
    {
        try {
            jsonObject = tradeService.getSpotOrderHistoryOfLast7Days();
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
