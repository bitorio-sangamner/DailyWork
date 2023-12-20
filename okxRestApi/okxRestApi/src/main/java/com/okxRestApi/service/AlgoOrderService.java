package com.okxRestApi.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.PlaceAlgoOrder;
import com.okex.open.api.service.trade.TradeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgoOrderService {

    @Autowired
    TradeAPIService tradeAPIService;

    @Autowired
    JSONObject jsonObject;

    public JSONObject placeAlgoOrder(PlaceAlgoOrder placeAlgoOrderObj)
    {
        jsonObject=tradeAPIService.placeAlgoOrder(placeAlgoOrderObj);
        return jsonObject;
    }
}
