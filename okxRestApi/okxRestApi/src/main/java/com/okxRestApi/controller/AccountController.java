package com.okxRestApi.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.account.param.SetLeverage;
import com.okex.open.api.exception.APIException;
import com.okxRestApi.service.AccountDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountDataService accountDataService;

    @Autowired
    JSONObject jsonObject;
   @PostMapping("/setLeverage")
    public ResponseEntity<Object> setLeverage(@RequestBody SetLeverage leverageObj)
   {
       jsonObject=accountDataService.setLeverage(leverageObj);
       return new ResponseEntity<>(jsonObject, HttpStatus.OK);
   }

   @GetMapping("/getPosition")
    public ResponseEntity<Object> getPosition()
   {
       try
       {
          jsonObject=accountDataService.getPosition();
          return new ResponseEntity<>(jsonObject,HttpStatus.OK);
       }
       catch(APIException e)
       {
          jsonObject.put("message",e.getMessage());
          return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }

   @GetMapping("/getLeverage")
    public ResponseEntity<Object> getLeverage(@RequestBody JSONObject json)
   {
       try
       {
          String instrumentID=json.getString("instId");
          String marginmode=json.getString("mgnMode");

           jsonObject=accountDataService.getLeverage(instrumentID,marginmode);
           return new ResponseEntity<>(jsonObject,HttpStatus.OK);

       }
       catch(APIException e)
       {
          jsonObject.put("message",e.getMessage());
          return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
}

