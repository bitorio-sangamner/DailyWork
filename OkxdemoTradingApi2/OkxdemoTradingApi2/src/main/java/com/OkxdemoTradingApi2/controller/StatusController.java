package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.StatusDataService;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController
{
    @Autowired
    private StatusDataService statusDataService;

    @GetMapping("/StatusDataService")
    public ResponseEntity<Object> StatusDataService()
    {
        JSONObject json=new JSONObject();
        try
        {
            json=statusDataService.getStatus();
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
}
