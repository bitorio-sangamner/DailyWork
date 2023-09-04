package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.earn.param.Cancel;
import com.okex.open.api.bean.earn.param.Purchase;
import com.okex.open.api.bean.earn.param.Redeem;
import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.earn.EarnAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarnService
{
    @Autowired
    private static EarnAPIService earnAPIService;

    @Autowired
    private static APIConfiguration apiConfiguration;

    public EarnService(EarnAPIService earnAPIService, APIConfiguration aPIConfiguration) {
        this.earnAPIService = earnAPIService;
        this.apiConfiguration = aPIConfiguration;
    }


    public JSONObject getOffers()
    {
        JSONObject json=earnAPIService.getOffers("","","");
        return json;
    }

    public JSONObject purchase(Purchase purchase)
    {
        JSONObject json=earnAPIService.purchase(purchase);
        return json;
    }

    public JSONObject redeem(Redeem redeemObj)
    {
        JSONObject json=earnAPIService.redeem(redeemObj);
        return json;
    }

    public JSONObject cancelOrde(Cancel canceObj)
    {
        JSONObject json=earnAPIService.cancel(canceObj);
        return json;
    }

    public JSONObject getActiveOrders()
    {
        JSONObject json=earnAPIService.getActiveOrders("","","","");
        return json;
    }

    public JSONObject getHistoryOrders()
    {
        JSONObject json=earnAPIService.getHistoryOrders("","","","","","");
        return json;
    }
}
