package com.okxRestApi.service;


import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.account.param.SetLeverage;
import com.okex.open.api.service.account.AccountAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDataService {

    @Autowired
    AccountAPIService accountDataService;

    @Autowired
    JSONObject jsonObject;

    public JSONObject setLeverage(SetLeverage leverObj)
    {
        jsonObject=accountDataService.setLeverage(leverObj);
        return jsonObject;
    }

    public JSONObject getPosition()
    {
        jsonObject=accountDataService.getPositions("","","");
        return jsonObject;
    }

    public JSONObject getLeverage(String instId,String marginMode)
    {
        jsonObject=accountDataService.getLeverage(instId,marginMode);
        return jsonObject;
    }
}
