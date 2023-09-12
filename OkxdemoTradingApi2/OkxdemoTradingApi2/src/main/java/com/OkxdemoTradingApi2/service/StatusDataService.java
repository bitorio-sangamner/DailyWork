package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.service.status.StatusDataAPIService;
import org.springframework.stereotype.Service;

@Service
public class StatusDataService
{
    private final StatusDataAPIService statusDataAPIService;

    public StatusDataService(StatusDataAPIService statusDataAPIService) {
        this.statusDataAPIService = statusDataAPIService;
    }

    public JSONObject getStatus()
    {
        JSONObject json=statusDataAPIService.getStatus("");
        return json;
    }
}
