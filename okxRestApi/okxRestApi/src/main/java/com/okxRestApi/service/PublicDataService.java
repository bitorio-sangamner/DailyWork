package com.okxRestApi.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.service.account.AccountAPIService;
import com.okex.open.api.service.publicData.PublicDataAPIService;
import com.okex.open.api.service.publicData.impl.PublicDataAPI;
import com.okex.open.api.service.trade.TradeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicDataService {

    @Autowired
    PublicDataAPIService publicDataAPIService;

    @Autowired
    JSONObject jsonObject;

    public JSONObject getInstrumentData(String instrumentType)
    {
        jsonObject=publicDataAPIService.getInstruments(instrumentType,"","","");
        return jsonObject;
    }

    public JSONObject getSpotInstrumentData()
    {
        jsonObject=publicDataAPIService.getInstruments("SPOT","","","");
        return jsonObject;
    }
}
