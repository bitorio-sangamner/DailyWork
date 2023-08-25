package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.DemoAPIService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
