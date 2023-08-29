package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.DemoAPIService;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.account.param.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DemoAPIController {
    @Autowired
    private final DemoAPIService demoAPIService;

    public DemoAPIController(DemoAPIService demoAPIService) {
        this.demoAPIService = demoAPIService;
    }

   /* @GetMapping("/test-demo-api")
    public String testDemoAPI() {
        try {
            //String result = demoAPIService.callDemoAPI();
            //return "API Call Result: " + result;



        } catch (Exception e) {
            return "API Call Failed: " + e.getMessage();
        }
    }*/
    @GetMapping("/test-demo-api")
    public JSONObject testDemoAPI()
    {
        JSONObject json=demoAPIService.callDemoAPI();
        return json;
    }

    @GetMapping("/instument")
    public ResponseEntity<JSONObject> getInstrument()
    {
        JSONObject json=demoAPIService.getInstrument();
        //return json;

        return ResponseEntity.of(Optional.of(json));
    }

    @GetMapping("/accountConfig")
    public JSONObject getAccountConfig()
    {
        JSONObject json=demoAPIService.getAccountDetails();
        return json;
    }

    @GetMapping("/getBalanceOfCurrency/{currency}")
    public JSONObject getBalance(@PathVariable String currency)
    {
        JSONObject json=demoAPIService.getAmount(currency);
        return json;
    }

    @PostMapping("/setPositionMode")
    public JSONObject setPositionMode(@RequestBody SetPositionMode setPositionModeObj)
    {
        JSONObject json=demoAPIService.setPosionMode(setPositionModeObj);
        return json;
    }

    @PostMapping("/setLeverage")
    public JSONObject setLeverage(@RequestBody SetLeverage setLeverageObj)
    {
        JSONObject json=demoAPIService.setLeverage(setLeverageObj);
        return json;
    }

    @GetMapping("/getPositions")
    public JSONObject getPositions()
    {
        JSONObject json=demoAPIService.getPositions();
        return json;
    }

    @GetMapping("/getInterestRate/{ccy}")
    public JSONObject getInterestRate(@PathVariable String ccy)
    {
        JSONObject json=demoAPIService.getInterestRate(ccy);
        return json;
    }

    @GetMapping("/getLeverage/{instId}/{mgnMode}")
    public JSONObject getLeverage(@PathVariable String instId,@PathVariable String mgnMode)
    {
        JSONObject json=demoAPIService.getLeverage(instId,mgnMode);
        return json;
    }

    @GetMapping("/getAccountAndPosition/{instType}")
    public JSONObject getAccountAndPosition(@PathVariable String instType)
    {
        JSONObject json=demoAPIService.getAccountAndPosition(instType);
        return json;
    }

    @GetMapping("/getBillsDetails7Days")
    public JSONObject getBillsDetails7Days()
    {
        JSONObject json=demoAPIService.getBillsDetails7Days();
        return json;
    }

    @GetMapping("/getPositionTiers/{instType}/{uly}/{instFamily}")
    public JSONObject getPositionTiers(@PathVariable String instType,@PathVariable String uly,@PathVariable String instFamily)
    {
        JSONObject json=demoAPIService.getPositionTiers(instType,uly,instFamily);
        return json;
    }

    @PostMapping("/setRiskOffsetType")
    public JSONObject setRiskOffsetType(@RequestBody IncreaseDecreaseMargin increaseDecreaseMargin)
    {
        JSONObject json=demoAPIService.setRiskOffsetType(increaseDecreaseMargin);
        return json;
    }

    @GetMapping("/activateOption")
    public JSONObject activateOption()
    {
        JSONObject json=demoAPIService.activateOption();
        return json;
    }

    @GetMapping("/getAccountGreeks/{ccy}")
    public JSONObject getAccountGreeks(@PathVariable String ccy)
    {
        JSONObject json=demoAPIService.getAccountGreeks(ccy);
        return json;
    }

    @PostMapping("/setIsolatedMode")
    public JSONObject setIsolatedMode(@RequestBody SetIsolatedMode setIsolatedMode)
    {
        JSONObject json=demoAPIService.setIsolatedMode(setIsolatedMode);
        return json;
    }

    @GetMapping("/getFeeRates/{instType}/{instId}/{uly}/{instFamily}")
    public JSONObject getFeeRates(@PathVariable String instType,@PathVariable String instId,@PathVariable String uly,@PathVariable String instFamily)
    {
        JSONObject json=demoAPIService.getFeeRates(instType,instId,uly,instFamily);
        return json;
    }

    @PostMapping("/setAutoLoan")//--->Only cross-margin accounts that cross currencies can be set to borrow coins automatically
    public JSONObject setAutoLoan(@RequestBody SetAutoLoan setAutoLoanObj)
    {
        JSONObject json=demoAPIService.setAutoLoan(setAutoLoanObj);
        return json;
    }

    @GetMapping("/get_max_size_tradeable_instrument")
    public ResponseEntity<Object> getMaximumTradableSizeForInstrument(@RequestBody JSONObject jsonObject) {
        String instId = jsonObject.getString("instId");
        String tdMode = jsonObject.getString("tdMode");
        String ccy = jsonObject.getString("ccy");
        String px = jsonObject.getString("px");
        String leverage = jsonObject.getString("leverage");
        boolean unSpotOffset = jsonObject.getBoolean("unSpotOffset") != null && jsonObject.getBoolean("unSpotOffset");

        JSONObject json=demoAPIService.getMaximumTradableSizeForInstrument(instId,tdMode,ccy,px,leverage,unSpotOffset);
        return ResponseEntity.of(Optional.of(json));


    }

}
