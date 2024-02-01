package com.orderWithDataBase.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.gridTrading.param.AmendOrderAlgo;
import com.okex.open.api.bean.gridTrading.param.OrderAlgo;
import com.okex.open.api.bean.gridTrading.param.StopOrderAlgo;
import com.okex.open.api.service.gridTrading.GridTradingAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public JSONObject stopGridOrderFromOkx(StopOrderAlgo stopOrderAlgoObj)
    {
        jsonObject=gridTradingAPIService.stopOrderAlgo(stopOrderAlgoObj);
        return jsonObject;
    }

//    public JSONObject closePositionForGridContract(String algoId,String marketCloseAllPosition)
//    {
//
//    }

    public JSONObject getGridAlgoOrderPosition(String algoOrdType,String algoId)
    {
        jsonObject=gridTradingAPIService.getPositions(algoOrdType,algoId);
        return jsonObject;
    }

    public JSONObject getGridAlgoOrderList(String algoOrdType)
    {
        jsonObject=gridTradingAPIService.getGridAlgoOrderList(algoOrdType,"","","","","","");
        return jsonObject;
    }
    public JSONObject amendGridOrderFromOkx(AmendOrderAlgo amendOrderAlgoObj)
    {
        jsonObject=gridTradingAPIService.amendOrderAlgo(amendOrderAlgoObj);
        return jsonObject;
    }
}
