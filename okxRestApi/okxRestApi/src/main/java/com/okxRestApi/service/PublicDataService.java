package com.okxRestApi.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.service.publicData.PublicDataAPIService;
import com.okex.open.api.service.publicData.impl.PublicDataAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicDataService {

    @Autowired
    PublicDataAPIService publicDataAPIService;
    JSONObject jsonObject=new JSONObject();

    public JSONObject getInstrumentData()
    {
        jsonObject=publicDataAPIService.getInstruments("FUTURES","","","");
        return jsonObject;
    }
}
