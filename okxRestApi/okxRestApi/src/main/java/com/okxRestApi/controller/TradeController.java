package com.okxRestApi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.AmendOrder;
import com.okex.open.api.bean.trade.param.CancelOrder;
import com.okex.open.api.bean.trade.param.ClosePositions;
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

import java.util.List;

@RestController
public class TradeController {

    @Autowired
    TradeService tradeService;

    @Autowired
    PublicDataService publicDataService;

    @Autowired
    JSONObject jsonObject;

    // Constant for the "status" key
    private static final String STATUS_KEY = "status";
    private static final String MESSAGE_KEY="message";


    @PostMapping("/placeOrderOnOkx")
    public ResponseEntity<Object> placeOrderOnOkx(@RequestBody PlaceOrder orderObj)
    {
        try {
            jsonObject = tradeService.placeOrderOnOkx(orderObj);
            // Get the "data" array
            JSONArray dataArray = jsonObject.getJSONArray("data");

            // Get the first object in the array
            JSONObject dataObject = dataArray.getJSONObject(0);
            // Get the value of "sMsg"
            String sMsgValue = dataObject.getString("sMsg");

            System.out.println("message:"+sMsgValue);

            if(!sMsgValue.equals("Order placed"))
            {
                dataObject.put(STATUS_KEY,HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(jsonObject,HttpStatus.BAD_REQUEST);
            }
            dataObject.put(STATUS_KEY,HttpStatus.CREATED);
            return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
        }
        catch(APIException e)
        {

            jsonObject.put(MESSAGE_KEY,e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//placeOrderOnOkx

    @GetMapping("/orderDetails")
    public ResponseEntity<Object> getOrderDetailsFromOkx(@RequestBody JSONObject json)
    {
        try {
            String instrumentId = json.getString("instId");
            String orderId = json.getString("ordId");
            String clientOrderId = json.getString("clOrdId");

            jsonObject = tradeService.getOrderDetails(instrumentId, orderId, clientOrderId);

            String msg=jsonObject.getString("msg");
            System.out.println("message:"+msg);

            if(jsonObject.get("msg").equals(""))
            {
                jsonObject.put(STATUS_KEY,HttpStatus.FOUND);
                return new ResponseEntity<>(jsonObject,HttpStatus.FOUND);
            }
            jsonObject.put(STATUS_KEY,HttpStatus.NOT_ACCEPTABLE);
            return new ResponseEntity<>(jsonObject, HttpStatus.NOT_ACCEPTABLE);
        }
        catch(APIException e)
        {

            jsonObject.put(MESSAGE_KEY,e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/amendOrder")
    public ResponseEntity<Object> amendOrderFromOkx(@RequestBody AmendOrder amendOrderObj)
    {
        try {
            jsonObject = tradeService.amendOrder(amendOrderObj);

            if(jsonObject.get("msg").equals(""))
            {
                jsonObject.put(STATUS_KEY,HttpStatus.OK);
                return new ResponseEntity<>(jsonObject,HttpStatus.OK);
            }
            jsonObject.put(STATUS_KEY,HttpStatus.NOT_ACCEPTABLE);
            return new ResponseEntity<>(jsonObject, HttpStatus.NOT_ACCEPTABLE);
        }
        catch(APIException e)
        {
            jsonObject.put(MESSAGE_KEY,e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cancelOrderFromOkx")
    public ResponseEntity<Object> cancelOrderFromOkx(@RequestBody CancelOrder cancelOrderObj)
    {
        try {
            jsonObject = tradeService.cancelOrderFromOkx(cancelOrderObj);
            if(jsonObject.get("msg").equals(""))
            {
                jsonObject.put(STATUS_KEY,HttpStatus.OK);
                return new ResponseEntity<>(jsonObject,HttpStatus.OK);
            }

            jsonObject.put(STATUS_KEY,HttpStatus.NOT_ACCEPTABLE);
            return new ResponseEntity<>(jsonObject, HttpStatus.NOT_ACCEPTABLE);
        }
        catch(APIException e)
        {
            jsonObject.put(MESSAGE_KEY,e.getMessage());
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
            jsonObject.put(MESSAGE_KEY,e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/closePosition")
    public ResponseEntity<Object> closePositionFromOkx(@RequestBody ClosePositions closePositionsObj)
    {
        try {
            jsonObject = tradeService.closePositionFromOkx(closePositionsObj);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {
            jsonObject.put(MESSAGE_KEY,e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getSpotOrderHistoryLast7Days")
    public ResponseEntity<Object> getSpotOrderHistoryOfLast7Day(@RequestBody JSONObject json)
    {
        try {
            String instrumentType = json.getString("instType");

            jsonObject = tradeService.getSpotOrderHistoryOfLast7Days(instrumentType);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {
            jsonObject.put(MESSAGE_KEY,e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getFutureOrderHistoryLast7Days")
    public ResponseEntity<Object> getFutureOrderHistoryOfLast7Day(@RequestBody JSONObject json)
    {
        try {
            String instrumentType = json.getString("instType");
            jsonObject = tradeService.getFutureOrderHistoryOfLast7Days(instrumentType);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {
            jsonObject.put(MESSAGE_KEY,e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getSpotOrderHistoryLast3Months")
    public ResponseEntity<Object> getSpotOrderHistoryLast3Days(@RequestBody JSONObject json)
    {
       try
       {
          String instrumentType=json.getString("instType");
          jsonObject=tradeService.getSpotOrderHistoryOfLast3Months(instrumentType);
          return new ResponseEntity<>(jsonObject,HttpStatus.OK);
       }
       catch(APIException e)
       {
          jsonObject.put(MESSAGE_KEY,e.getMessage());
          return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/getFutureOrderHistoryLast3Months")
    public ResponseEntity<Object> getFutureOrderHistoryLast3Months(@RequestBody JSONObject json)
    {
       try
       {
          String instrumentType=json.getString("instType");
           jsonObject=tradeService.getFutureOrderHistoryOfLast3Months(instrumentType);

           return new ResponseEntity<>(jsonObject,HttpStatus.OK);

       }
       catch(APIException e)
       {
          jsonObject.put(MESSAGE_KEY,e.getMessage());
          return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PostMapping("/placeMultipleOrdersOnOkx")
    public ResponseEntity<Object> placeMultipleOrders(@RequestBody List<PlaceOrder> ordersList)
    {
       try
       {
          jsonObject=tradeService.placeMultipleOrders(ordersList);
          return new ResponseEntity<>(jsonObject,HttpStatus.OK);
       }
       catch(APIException e)
       {
          jsonObject.put(MESSAGE_KEY,e.getMessage());
          return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }


}
