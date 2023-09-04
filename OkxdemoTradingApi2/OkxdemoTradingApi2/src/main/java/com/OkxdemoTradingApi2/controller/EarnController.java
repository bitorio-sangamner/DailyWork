package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.EarnService;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.earn.param.Cancel;
import com.okex.open.api.bean.earn.param.Purchase;
import com.okex.open.api.bean.earn.param.Redeem;
import com.okex.open.api.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class EarnController
{
    @Autowired
    private EarnService earnService;

    @GetMapping("/getOffers")
    public ResponseEntity<Object> getOffers()
    {
        JSONObject json=new JSONObject();
        try {


             json = earnService.getOffers();
        }
        catch(APIException e)
        {
            JSONObject  jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("msg",msg);
            return ResponseEntity.of(Optional.of(jsonObj));
        }
        return ResponseEntity.of(Optional.of(json));
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchase(@RequestBody Purchase purchaseObj)
    {
        JSONObject json=new JSONObject();
        try {
             json = earnService.purchase(purchaseObj);
        }

        catch(APIException e)
        {
            JSONObject  jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("msg",msg);
            return ResponseEntity.of(Optional.of(jsonObj));
        }
        return ResponseEntity.of(Optional.of(json));
    }

    @PostMapping("/redeem")
    public ResponseEntity<Object> redeem(@RequestBody Redeem redeemObj)
    {
        JSONObject json=new JSONObject();
        try {
             json = earnService.redeem(redeemObj);
        }

        catch(APIException e)
        {
            JSONObject  jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("msg",msg);
            return ResponseEntity.of(Optional.of(jsonObj));
        }

        return ResponseEntity.of(Optional.of(json));
    }

    @PostMapping("/cancelOrde")
    public ResponseEntity<Object> cancelOrde(@RequestBody Cancel canceObj)
    {
        JSONObject json=new JSONObject();
        try {
             json = earnService.cancelOrde(canceObj);
        }

        catch(APIException e)
        {
            JSONObject  jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("msg",msg);
            return ResponseEntity.of(Optional.of(jsonObj));
        }
        return ResponseEntity.of(Optional.of(json));
    }

    @GetMapping("/getActiveOrders")
    public ResponseEntity<Object> getActiveOrders()
    {
        JSONObject json=new JSONObject();
        try {


             json = earnService.getActiveOrders();
        }
        catch(APIException e)
        {
            JSONObject  jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("msg",msg);
            return ResponseEntity.of(Optional.of(jsonObj));
        }
        return ResponseEntity.of(Optional.of(json));
    }
}
