package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.TradeService;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.AmendOrder;
import com.okex.open.api.bean.trade.param.CancelOrder;
import com.okex.open.api.bean.trade.param.ClosePositions;
import com.okex.open.api.bean.trade.param.PlaceOrder;
import com.okex.open.api.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Object> placeOrder(@RequestBody PlaceOrder placeOrderObj)
    {
        JSONObject json=new JSONObject();
       try
       {
           json=tradeService.placeOrder(placeOrderObj);
       }

       catch(APIException e)
       {
           JSONObject jsonObject=new JSONObject();

           String msg=e.getMessage();
           jsonObject.put("message",msg);
           return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/placeMultipleOrders")
    public ResponseEntity<Object> placeMultipleOrders(@RequestBody List<PlaceOrder> placeOrdersListObj)
    {
        JSONObject json=new JSONObject();
       try
       {
            json=tradeService.placeMultipleOrders(placeOrdersListObj);
       }

       catch(APIException e)
       {
           JSONObject jsonObject=new JSONObject();

           String msg=e.getMessage();
           jsonObject.put("message",msg);
           return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/cancelOrder")
    public ResponseEntity cancelOrder(@RequestBody CancelOrder cancelOrderObj)
    {
        JSONObject json=new JSONObject();
       try
       {
           json=tradeService.cancelOrder(cancelOrderObj);
       }
       catch(APIException e)
       {
           JSONObject jsonObject=new JSONObject();

           String msg=e.getMessage();
           jsonObject.put("message",msg);
           return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/cancelMultipleOrders")
    public ResponseEntity<Object> cancelMultipleOrders(@RequestBody List<CancelOrder> cancelOrdersObj)
    {
        JSONObject json=new JSONObject();
       try
       {
           json=tradeService.cancelMultipleOrders(cancelOrdersObj);
       }

       catch(APIException e)
       {
           JSONObject jsonObject=new JSONObject();

           String msg=e.getMessage();
           jsonObject.put("message",msg);
           return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/amendOrder")
    public ResponseEntity<Object> amendOrder(@RequestBody AmendOrder amendOrderObj)
    {
        JSONObject json=new JSONObject();
       try
       {
           json=tradeService.amendOrder(amendOrderObj);
       }

       catch(APIException e)
       {
           JSONObject jsonObject=new JSONObject();

           String msg=e.getMessage();
           jsonObject.put("message",msg);
           return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/amendMultipleOrders")
    public ResponseEntity<Object> amendMultipleOrders(@RequestBody List<AmendOrder> amendOrdersList)
    {
        JSONObject json=new JSONObject();
       try
       {
           json=tradeService.amendMultipleOrders(amendOrdersList);
       }

       catch(APIException e)
       {
           JSONObject jsonObject=new JSONObject();

           String msg=e.getMessage();
           jsonObject.put("message",msg);
           return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/closePositions")
    public ResponseEntity<Object> closePositions(@RequestBody ClosePositions closePositionsObj)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=tradeService.closePositions(closePositionsObj);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();

            String msg=e.getMessage();
            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getOrderDetails")
    public ResponseEntity<Object> getOrderDetails(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try
        {
            String instId=jsonObject.getString("instId");
            String ordId=jsonObject.getString("ordId");
            String clOrdId=jsonObject.getString("clOrdId");

            json=tradeService.getOrderDetails(instId,ordId,clOrdId);
        }

        catch(APIException e)
        {
            JSONObject jsonObject2=new JSONObject();

            String msg=e.getMessage();
            jsonObject2.put("message",msg);
            return new ResponseEntity<>(jsonObject2, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getOrderList")
    public ResponseEntity<Object> getOrderList()
    {
        JSONObject json=new JSONObject();
        try
        {
            json=tradeService.getOrderList();
        }

        catch(APIException e)
        {
            JSONObject jsonObject2=new JSONObject();

            String msg=e.getMessage();
            jsonObject2.put("message",msg);
            return new ResponseEntity<>(jsonObject2, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getOrderHistory7days/{instType}")
    public ResponseEntity<Object> getOrderHistory7days(@PathVariable String instType)
    {
        JSONObject json=new JSONObject();

        try
        {
            json=tradeService.getOrderHistory7days(instType);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();

            String msg=e.getMessage();
            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getOrderHistory3months/{instType}")
    public ResponseEntity<Object> getOrderHistory3months(@PathVariable String instType)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=tradeService.getOrderHistory3months(instType);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();

            String msg=e.getMessage();
            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getTransactionDetails")
    public ResponseEntity<Object> getTransactionDetails()
    {
        JSONObject json=new JSONObject();
        try
        {
            json=tradeService.getTransactionDetails();
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();

            String msg=e.getMessage();
            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getTransactionDetailsForThreeMonths/{instType}")
    public ResponseEntity<Object> getTransactionDetailsForThreeMonths(@PathVariable String instType)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=tradeService.getTransactionDetailsForThreeMonths(instType);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();

            String msg=e.getMessage();
            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
