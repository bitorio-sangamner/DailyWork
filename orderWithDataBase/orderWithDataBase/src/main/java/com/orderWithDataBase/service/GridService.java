package com.orderWithDataBase.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.gridTrading.param.OrderAlgo;
import com.okex.open.api.service.gridTrading.GridTradingAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GridService {

    @Autowired
    private GridTradingAPIService gridTradingAPIService;

    @Autowired
    private JSONObject jsonObject;

    public JSONObject placeGridOrderOnOkx(OrderAlgo orderAlgoObj)
    {
        jsonObject=gridTradingAPIService.orderAlgo(orderAlgoObj);
        return jsonObject;
    }

    public JSONObject getGridOrderDetailsFromOkx(String algoOrderType,String algoId)
    {
        jsonObject=gridTradingAPIService.getOrdersAlgoDetails(algoOrderType,algoId);
        return jsonObject;
    }
}
