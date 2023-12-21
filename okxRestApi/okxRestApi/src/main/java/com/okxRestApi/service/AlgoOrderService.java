package com.okxRestApi.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.CancelAlgoOrder;
import com.okex.open.api.bean.trade.param.PlaceAlgoOrder;
import com.okex.open.api.service.trade.TradeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public JSONObject getAlgoOrderDetailsFromOkx(String algoId,String clientsuppliedAlgoID)
    {
        jsonObject=tradeAPIService.getAlgoOrderDetails(algoId,clientsuppliedAlgoID);
        return jsonObject;
    }

    public JSONObject cancelAlgoOrder(List<CancelAlgoOrder> cancelAlgoOrderObj)
    {
        jsonObject=tradeAPIService.cancelAlgoOrder(cancelAlgoOrderObj);
        return jsonObject;
    }
}
