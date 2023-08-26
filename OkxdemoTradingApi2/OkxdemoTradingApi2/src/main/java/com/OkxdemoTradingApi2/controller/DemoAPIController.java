package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.DemoAPIService;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.account.param.SetLeverage;
import com.okex.open.api.bean.account.param.SetPositionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public JSONObject getInstrument()
    {
        JSONObject json=demoAPIService.getInstrument();
        return json;
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

    @GetMapping("/getPositions/{instType}")
    public JSONObject getPositions(@PathVariable("instType") String instType)
    {
        JSONObject json=demoAPIService.getPositions(instType,"null","null");
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

}
