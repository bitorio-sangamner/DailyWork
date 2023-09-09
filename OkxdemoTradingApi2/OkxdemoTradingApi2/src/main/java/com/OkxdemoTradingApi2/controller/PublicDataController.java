package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.PublicDataService;
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
public class PublicDataController {
    @Autowired
    private PublicDataService publicDataService;

    @GetMapping("/getInstruments")
    public ResponseEntity<Object> getInstruments(@RequestBody JSONObject jsonObject) {
        JSONObject json = new JSONObject();
        try {
            String instType = jsonObject.getString("instType");
            String uly = jsonObject.getString("uly");
            String instFamily = jsonObject.getString("instFamily");
            String instId = jsonObject.getString("instId");

            json = publicDataService.getInstruments(instType, uly, instFamily, instId);
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getDeliveryExerciseHistory")
    public ResponseEntity<Object> getDeliveryExerciseHistory(@RequestBody JSONObject jsonObject) {
        JSONObject json = new JSONObject();
        try {
            String instType = jsonObject.getString("instType");
            String uly = jsonObject.getString("uly");
            String instFamily = jsonObject.getString("instFamily");
            String after = jsonObject.getString("after");
            String before = jsonObject.getString("before");
            String limit = jsonObject.getString("limit");

            json = publicDataService.getDeliveryExerciseHistory(instType, uly, instFamily, after, before, limit);
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getOpenInterest")
    public ResponseEntity<Object> getOpenInterest(@RequestBody JSONObject jsonObject) {
        JSONObject json = new JSONObject();
        try {
            String instType = jsonObject.getString("instType");
            String uly = jsonObject.getString("uly");
            String instFamily = jsonObject.getString("instFamily");
            String instId = jsonObject.getString("instId");

            json = publicDataService.getOpenInterest(instType, uly, instFamily, instId);
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getFundingRate/{instId}")
    public ResponseEntity<Object> getFundingRate(@PathVariable String instId) {
        JSONObject json = new JSONObject();
        try {
            json = publicDataService.getFundingRate(instId);
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getFundingRateHistory")
    public ResponseEntity<Object> getFundingRateHistory(@RequestBody JSONObject jsonObject) {
        JSONObject json = new JSONObject();
        try {
            String instId = jsonObject.getString("instId");
            String after = jsonObject.getString("after");
            String before = jsonObject.getString("before");
            String limit = jsonObject.getString("limit");

            json = publicDataService.getFundingRateHistory(instId, after, before, limit);

        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getLimitPrice/{instId}")
    public ResponseEntity<Object> getLimitPrice(@PathVariable String instId) {
        JSONObject json = new JSONObject();
        try {
            json = publicDataService.getLimitPrice(instId);
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getOptionMarketData")
    public ResponseEntity<Object> getOptionMarketData(@RequestBody JSONObject jsonObject) {
        JSONObject json = new JSONObject();
        try {
            String uly = jsonObject.getString("uly");
            String instFamily = jsonObject.getString("instFamily");
            String expTime = jsonObject.getString("expTime");

            json = publicDataService.getOptionMarketData(uly, instFamily, expTime);
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getEstimatedDeliveryExcercisePrice/{instId}")
    public ResponseEntity<Object> getEstimatedDeliveryExcercisePrice(@PathVariable String instId) {
        JSONObject json = new JSONObject();
        try {
            json = publicDataService.getEstimatedDeliveryExcercisePrice(instId);
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getDiscountRateAndInterestFreeQuota/{currencyName}")
    public ResponseEntity<Object> getDiscountRateAndInterestFreeQuota(@PathVariable String currencyName) {
        JSONObject json = new JSONObject();
        try {
            json = publicDataService.getDiscountRateAndInterestFreeQuota(currencyName, "");
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getSystemTime")
    public ResponseEntity<Object> getSystemTime() {
        JSONObject json = new JSONObject();
        try {
            json = publicDataService.getSystemTime();
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getMarkPrice")
    public ResponseEntity<Object> getMarkPrice(@RequestBody JSONObject jsonObject) {
        JSONObject json = new JSONObject();
        try {
            String instType = jsonObject.getString("instType");
            String uly = jsonObject.getString("uly");
            String instFamily = jsonObject.getString("instFamily");
            String instId = jsonObject.getString("instId");

            json = publicDataService.getMarkPrice(instType, uly, instFamily, instId);
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getTier")
    public ResponseEntity<Object> getTier(@RequestBody JSONObject jsonObject) {
        JSONObject json = new JSONObject();
        try {
            String instType = jsonObject.getString("instType");
            String uly = jsonObject.getString("uly");
            String instFamily = jsonObject.getString("instFamily");
            String instId = jsonObject.getString("instId");
            String tdMode = jsonObject.getString("tdMode");
            String tier = jsonObject.getString("tier");

            json = publicDataService.getTier(instType, uly, instFamily, instId, tdMode, tier);
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getInterestRateLoanQuota")
    public ResponseEntity<Object> getInterestRateLoanQuota() {
        JSONObject json = new JSONObject();
        try {
            json = publicDataService.getInterestRateLoanQuota();
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getUnderlying/{instType}")
    public ResponseEntity<Object> getUnderlying(@PathVariable String instType) {
        JSONObject json = new JSONObject();
        try {
            json = publicDataService.getUnderlying(instType);
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getVipInterestRateLoanQuota")
    public ResponseEntity<Object> getVipInterestRateLoanQuota() {
        JSONObject json = new JSONObject();
        try {
            json = publicDataService.getVipInterestRateLoanQuota();
        } catch (APIException e) {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getInsuranceFund/{instType}/{instFamily}")
    public ResponseEntity<Object> getInsuranceFund(@PathVariable String instType, @PathVariable String instFamily)
    {
        JSONObject json = new JSONObject();
        try {
            json = publicDataService.getInsuranceFund(instType,"","",instFamily,"","","","");
        } catch (APIException e)
        {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getConvertContractCoin/{instId}/{size}")
    public ResponseEntity<Object> getConvertContractCoin(@PathVariable String instId,@PathVariable String size)
    {
        JSONObject json = new JSONObject();
        try
        {
            json=publicDataService.getConvertContractCoin("",instId,size,"","");
        }

        catch(APIException e)
        {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getOptionTrades/{instId}/{instFamily}")
    public ResponseEntity<Object> getOptionTrades(@PathVariable String instId,@PathVariable String instFamily)
    {
        JSONObject json = new JSONObject();
        try
        {
            json=publicDataService.getOptionTrades(instId,instFamily,"");
        }

        catch(APIException e)
        {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping("/getInstrumentTickBands/{instType}")
    public ResponseEntity<Object> getInstrumentTickBands(@PathVariable String instType)
    {
        JSONObject json = new JSONObject();
        try
        {
            json= publicDataService.getInstrumentTickBands(instType,"");
        }
        catch(APIException e)
        {
            JSONObject jsonObj = new JSONObject();
            String msg = e.getMessage();

            jsonObj.put("message", msg);
            return new ResponseEntity(jsonObj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }
}

