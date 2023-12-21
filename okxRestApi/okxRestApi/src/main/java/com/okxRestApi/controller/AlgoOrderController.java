package com.okxRestApi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.CancelAlgoOrder;
import com.okex.open.api.bean.trade.param.PlaceAlgoOrder;
import com.okex.open.api.exception.APIException;
import com.okxRestApi.service.AlgoOrderService;
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

    @Autowired
    AlgoOrderService algoOrderService;

    @Autowired
    JSONObject jsonObject;
    @PostMapping("/placeAlgoOrder")
    public ResponseEntity<Object> placeAlgoOrder(@RequestBody PlaceAlgoOrder algoOrderObj)
    {
        try {
            jsonObject = algoOrderService.placeAlgoOrder(algoOrderObj);
            if(jsonObject.get("msg").equals(""))
            {
                jsonObject.put("status",HttpStatus.CREATED);
                return new ResponseEntity<>(jsonObject,HttpStatus.CREATED);
            }
            jsonObject.put("status",HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//placeAlgoOrder

    @GetMapping("/getAlgoOrderDetailsFromOkx")
    public ResponseEntity<Object> getAlgoOrderDetailsFromOkx(@RequestBody JSONObject json)
    {
       try
       {
           String algoId=json.getString("algoId");
           String clientsuppliedAlgoID=json.getString("algoClOrdId");

           jsonObject=algoOrderService.getAlgoOrderDetailsFromOkx(algoId,clientsuppliedAlgoID);

           if(jsonObject.getString("msg").equals("Order does not exist"))
           {
              jsonObject.put("status",HttpStatus.BAD_REQUEST);
              return new ResponseEntity<>(jsonObject,HttpStatus.BAD_REQUEST);
           }

           jsonObject.put("status",HttpStatus.FOUND);
           return new ResponseEntity<>(jsonObject,HttpStatus.FOUND);
       }
       catch(APIException e)
       {
          jsonObject.put("message",e.getMessage());
          return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }//getAlgoOrderDetailsFromOkx

    @PostMapping("/cancelAlgoOrder")
    public ResponseEntity<Object> cancelAlgoOrder(@RequestBody List<CancelAlgoOrder> cancelAlgoOrderList)
    {
        try
        {
           jsonObject=algoOrderService.cancelAlgoOrder(cancelAlgoOrderList);
           return new ResponseEntity<>(jsonObject,HttpStatus.OK);
        }
        catch(APIException e)
        {
           jsonObject.put("message",e.getMessage());
           return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
