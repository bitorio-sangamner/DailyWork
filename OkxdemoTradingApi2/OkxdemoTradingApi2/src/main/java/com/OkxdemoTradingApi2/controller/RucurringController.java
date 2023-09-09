package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.RecurringService;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.recurringTrading.Recurring;
import com.okex.open.api.bean.recurringTrading.RecurringAlgoOrder;
import com.okex.open.api.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RucurringController {

    @Autowired
    private RecurringService recurringService;

    @PostMapping("/placeOrderAlgo")
    public ResponseEntity<Object> placeOrderAlgo(@RequestBody RecurringAlgoOrder recurringOrderObj)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=recurringService.placeOrderAlgo(recurringOrderObj);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @PostMapping("/amendOrderAlgo")
    public ResponseEntity<Object> amendOrderAlgoFromRecurring(@RequestBody Recurring recurringObj)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=recurringService.amendOrderAlgoFromRecurring(recurringObj);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getOrderAlgoDetails/{algoId}")
    public ResponseEntity<Object> getOrderAlgoDetails(@PathVariable String algoId)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=recurringService.getOrderAlgoDetails(algoId);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getOrderAlgoPending")
    public ResponseEntity<Object> getOrderAlgoPending()
    {
        JSONObject json=new JSONObject();
        try
        {
            json=recurringService.getOrderAlgoPending();
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getOrderAlgoHistory/{algoId}")
    public ResponseEntity<Object> getOrderAlgoHistory(@PathVariable String algoId)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=recurringService.getOrderAlgoHistory(algoId);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @PostMapping("/stopOrderAlgo")
    public ResponseEntity<Object> stopOrderAlgo(@RequestBody List<Recurring> recurring)
    {
        JSONObject json=new JSONObject();

        try
        {
            json=recurringService.stopOrderAlgo(recurring);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    /*@GetMapping("/getSubOrders/{algoId}")
    public ResponseEntity<Object> getSubOrders(@PathVariable String algoId)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=recurringService.getSubOrders(algoId);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json,HttpStatus.OK);
    }*/
}
