package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.MarketDataService;
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
public class MarketDataController
{
    @Autowired
    private MarketDataService marketDataServiceObj;

    @GetMapping("/getTickers")
    public ResponseEntity<Object> getTickers(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try
        {
           String instType= jsonObject.getString("instType");
           String instFamily=jsonObject.getString("instFamily");
           String uly=jsonObject.getString("uly");

            json=marketDataServiceObj.getTickers(instType,instFamily,uly);
        }

        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getTicker/{instId}")
    public ResponseEntity<Object> getTicker(@PathVariable String instId)
    {
        JSONObject json=new JSONObject();
       try
       {
           json=marketDataServiceObj.getTicker(instId);
       }
       catch(APIException e)
       {
           JSONObject jsonObj=new JSONObject();
           String msg=e.getMessage();
           jsonObj.put("message",msg);
           return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getIndexTickers")
    public ResponseEntity<Object> getIndexTickers(@RequestBody JSONObject jsonObj)
    {
        JSONObject json=new JSONObject();
        try
        {
          String quoteCcy=jsonObj.getString("quoteCcy");
          String instId=jsonObj.getString("instId");

            json=marketDataServiceObj.getIndexTickers(quoteCcy,instId);
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

    @GetMapping("/getOrderBook")
    public ResponseEntity<Object> getOrderBook(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try {
            String instId=jsonObject.getString("instId");
            String sz=jsonObject.getString("sz");

            json=marketDataServiceObj.getOrderBook(instId,sz);
        }

        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getOrderLiteBook")
    public ResponseEntity<Object>getOrderLiteBook(String instId)
    {
        JSONObject json=new JSONObject();
       try
       {
           json=marketDataServiceObj.getOrderLiteBook(instId);
       }

       catch(APIException e)
       {
           JSONObject jsonObj=new JSONObject();
           String msg=e.getMessage();
           jsonObj.put("message",msg);
           return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getCandlesticks")
    public ResponseEntity<Object> getCandlesticks(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try
        {
            String instId=jsonObject.getString("instId");
            String after=jsonObject.getString("after");
            String before=jsonObject.getString("before");
            String bar=jsonObject.getString("bar");
            String limit=jsonObject.getString("limit");

            json=marketDataServiceObj.getCandlesticks(instId,after,before,bar,limit);
        }

        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getCandlesticksHistory")
    public ResponseEntity<Object> getCandlesticksHistory(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
       try
       {
           String instId=jsonObject.getString("instId");
           String after=jsonObject.getString("after");
           String before=jsonObject.getString("before");
           String bar= jsonObject.getString("bar");
           String limit=jsonObject.getString("limit");

           json=marketDataServiceObj.getCandlesticksHistory(instId,after,before,bar,limit);
       }

       catch(APIException e)
       {
           JSONObject jsonObj=new JSONObject();
           String msg=e.getMessage();
           jsonObj.put("message",msg);
           return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getIndexCandlesticks")
    public ResponseEntity<Object> getIndexCandlesticks(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
       try
       {
           String instId=jsonObject.getString("instId");
           String after=jsonObject.getString("after");
           String before=jsonObject.getString("before");
           String bar=jsonObject.getString("bar");
           String limit=jsonObject.getString("limit");

           json=marketDataServiceObj.getIndexCandlesticks(instId,after,before,bar,limit);
       }

       catch(APIException e)
       {
           JSONObject jsonObj=new JSONObject();
           String msg=e.getMessage();
           jsonObj.put("message",msg);
           return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getIndexCandlesticksHistory")
    public ResponseEntity<Object> getIndexCandlesticksHistory(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try
        {
            String instId=jsonObject.getString("instId");
            String after=jsonObject.getString("after");
            String before=jsonObject.getString("before");
            String bar=jsonObject.getString("bar");
            String limit=jsonObject.getString("limit");

            json=marketDataServiceObj.getIndexCandlesticksHistory(instId,after,before,bar,limit);
        }

        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getMarkPriceCandlesticks")
    public ResponseEntity<Object> getMarkPriceCandlesticks(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
       try
       {
           String instId=jsonObject.getString("instId");
           String after=jsonObject.getString("after");
           String before=jsonObject.getString("before");
           String bar=jsonObject.getString("bar");
           String limit=jsonObject.getString("limit");

           json=marketDataServiceObj.getMarkPriceCandlesticks(instId,after,before,bar,limit);
       }

       catch(APIException e)
       {
           JSONObject jsonObj=new JSONObject();
           String msg=e.getMessage();
           jsonObj.put("message",msg);
           return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getMarkPriceCandlesticksHistory")
    public ResponseEntity<Object> getMarkPriceCandlesticksHistory(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try
        {
            String instId=jsonObject.getString("instId");
            String after=jsonObject.getString("after");
            String before=jsonObject.getString("before");
            String bar=jsonObject.getString("bar");
            String limit=jsonObject.getString("limit");

            json=marketDataServiceObj.getMarkPriceCandlesticksHistory(instId,after,before,bar,limit);
        }

        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getTrades")
    public ResponseEntity<Object> getTrades(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
       try
       {
           String instId=jsonObject.getString("instId");
           String limit=jsonObject.getString("limit");

           json=marketDataServiceObj.getTrades(instId,limit);
       }

       catch(APIException e)
       {
           JSONObject jsonObj=new JSONObject();
           String msg=e.getMessage();
           jsonObj.put("message",msg);
           return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getTradesHistory")
    public ResponseEntity<Object> getTradesHistory(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
       try
       {
           String instId=jsonObject.getString("instId");
           String after=jsonObject.getString("after");
           String before=jsonObject.getString("before");
           String limit=jsonObject.getString("limit");
           String type=jsonObject.getString("type");

           json=marketDataServiceObj.getTradesHistory(instId,after,before,limit,type);
       }

       catch(APIException e)
       {
           JSONObject jsonObj=new JSONObject();
           String msg=e.getMessage();
           jsonObj.put("message",msg);
           return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getTotalVolume")
    public ResponseEntity<Object> getTotalVolume()
    {
        JSONObject json=new JSONObject();
        try
        {
            json=marketDataServiceObj.getTotalVolume();
        }
        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getOracle")
    public ResponseEntity<Object> getOracle()
    {
        JSONObject json=new JSONObject();
        try
        {
            json=marketDataServiceObj.getOracle();
        }

        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getExchangeRate")
    public ResponseEntity<Object> getExchangeRate()
    {
        JSONObject json=new JSONObject();
        try
        {
            json=marketDataServiceObj.getExchangeRate();
        }

        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getIndexComponents/{index}")
    public ResponseEntity<Object> getIndexComponents(@PathVariable String index)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=marketDataServiceObj.getIndexComponents(index);
        }

        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getBlockTickers")
    public ResponseEntity<Object> getBlockTickers(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try
        {
            String instType=jsonObject.getString("instType");
            String uly=jsonObject.getString("uly");
            String instFamily=jsonObject.getString("instFamily");

            json=marketDataServiceObj.getBlockTickers(instType,uly,instFamily);
        }

        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getBlockTicker/{instId}")
    public ResponseEntity<Object> getBlockTicker(@PathVariable String instId)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=marketDataServiceObj.getBlockTicker(instId);
        }

        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getBlockTrades/{instId}")
    public ResponseEntity<Object> getBlockTrades(@PathVariable String instId)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=marketDataServiceObj.getBlockTrades(instId);
        }

        catch(APIException e)
        {
            JSONObject jsonObj=new JSONObject();
            String msg=e.getMessage();
            jsonObj.put("message",msg);
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
   @GetMapping("/getInstrumentFamilyTrades/{instFamily}")
    public ResponseEntity<Object> getInstrumentFamilyTrades(@PathVariable String instFamily)
   {
       JSONObject json=new JSONObject();
       try
       {
           json=marketDataServiceObj.getInstrumentFamilyTrades(instFamily);
       }

       catch(APIException e)
       {
           JSONObject jsonObj=new JSONObject();
           String msg=e.getMessage();
           jsonObj.put("message",msg);
           return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>(json, HttpStatus.OK);
   }


}
