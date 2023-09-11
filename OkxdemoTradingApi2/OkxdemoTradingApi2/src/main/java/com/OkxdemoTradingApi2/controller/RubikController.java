package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.RubikService;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RubikController
{
    @Autowired
    private RubikService rubikService;

    @GetMapping("/getSupportCoin")
    public ResponseEntity<Object> getSupportCoin()
    {
        JSONObject json=new JSONObject();
        try
        {
            json=rubikService.getSupportCoin();
        }

        catch(APIException e)
        {
          JSONObject jsonObject=new JSONObject();
          String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getTakerVolume")
    public ResponseEntity<Object> getTakerVolume(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try
        {
            String ccy=jsonObject.getString("ccy");
            String instType=jsonObject.getString("instType");
            String begin=jsonObject.getString("begin");
            String end=jsonObject.getString("end");
            String period=jsonObject.getString("period");

            json=rubikService.getTakerVolume(ccy,instType,begin,end,period);

        }

        catch(APIException e)
        {
            JSONObject jsonObject2=new JSONObject();
            String msg=e.getMessage();

            jsonObject2.put("message",msg);
            return new ResponseEntity(jsonObject2, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getLoanRatio/{currencyName}")
    public ResponseEntity<Object> getLoanRatio(@PathVariable String currencyName)
    {
        JSONObject json=new JSONObject();

        try
        {
            json=rubikService.getLoanRatio(currencyName);
        }

        catch(APIException e)
        {
            JSONObject jsonObject2=new JSONObject();
            String msg=e.getMessage();

            jsonObject2.put("message",msg);
            return new ResponseEntity(jsonObject2, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getLongShortAccountRatio/{currencyName}")
    public ResponseEntity<Object> getLongShortAccountRatio(@PathVariable String currencyName)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=rubikService.getLongShortAccountRatio(currencyName);
        }

        catch(APIException e)
        {
            JSONObject jsonObject2=new JSONObject();
            String msg=e.getMessage();

            jsonObject2.put("message",msg);
            return new ResponseEntity(jsonObject2, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getOpenInterestVolume/{ccy}")
    public ResponseEntity<Object> getOpenInterestVolume(@PathVariable String ccy)
    {
        JSONObject json=new JSONObject();
        try
        {
            json= rubikService.getOpenInterestVolume(ccy);
        }

        catch(APIException e)
        {
            JSONObject jsonObject2=new JSONObject();
            String msg=e.getMessage();

            jsonObject2.put("message",msg);
            return new ResponseEntity(jsonObject2, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getOptionOpenInterestVolume/{ccy}")
    public ResponseEntity<Object> getOptionOpenInterestVolume(@PathVariable String ccy)
    {
        JSONObject json=new JSONObject();
       try
       {
           json=rubikService.getOptionOpenInterestVolume(ccy);
       }

       catch(APIException e)
       {
           JSONObject jsonObject2=new JSONObject();
           String msg=e.getMessage();

           jsonObject2.put("message",msg);
           return new ResponseEntity(jsonObject2, HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getOpenInterestVolumeRatio/{currencyName}")
    public ResponseEntity<Object> getOpenInterestVolumeRatio(@PathVariable String currencyName)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=rubikService.getOpenInterestVolumeRatio(currencyName);
        }

        catch(APIException e)
        {
            JSONObject jsonObject2=new JSONObject();
            String msg=e.getMessage();

            jsonObject2.put("message",msg);
            return new ResponseEntity(jsonObject2, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getOpenInterestVolumeExpiry/{currencyName}")
    public ResponseEntity<Object> getOpenInterestVolumeExpiry(@PathVariable String currencyName)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=rubikService.getOpenInterestVolumeExpiry(currencyName);
        }

        catch(APIException e)
        {
            JSONObject jsonObject2=new JSONObject();
            String msg=e.getMessage();

            jsonObject2.put("message",msg);
            return new ResponseEntity(jsonObject2, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getOpenInterestVolumeStrike/{ccy}/{expDate}")
    public ResponseEntity<Object> getOpenInterestVolumeStrike(@PathVariable String ccy,@PathVariable String expDate)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=rubikService.getOpenInterestVolumeStrike(ccy,expDate);
        }

        catch(APIException e)
        {
            JSONObject jsonObject2=new JSONObject();
            String msg=e.getMessage();

            jsonObject2.put("message",msg);
            return new ResponseEntity(jsonObject2, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }
  @GetMapping("/getTakerBlockVolume/{ccy}")
    public ResponseEntity<Object> getTakerBlockVolume(@PathVariable String ccy)
  {
      JSONObject json=new JSONObject();
     try
     {
         json=rubikService.getTakerBlockVolume(ccy);
     }

     catch(APIException e)
     {
         JSONObject jsonObject2=new JSONObject();
         String msg=e.getMessage();

         jsonObject2.put("message",msg);
         return new ResponseEntity(jsonObject2, HttpStatus.BAD_REQUEST);
     }
      return new ResponseEntity<>(json,HttpStatus.OK);
  }
}
