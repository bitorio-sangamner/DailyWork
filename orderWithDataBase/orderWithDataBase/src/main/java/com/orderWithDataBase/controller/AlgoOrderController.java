package com.orderWithDataBase.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.AmendAlgos;
import com.okex.open.api.bean.trade.param.CancelAlgoOrder;
import com.okex.open.api.bean.trade.param.PlaceAlgoOrder;
import com.okex.open.api.exception.APIException;
import com.orderWithDataBase.entities.AlgoOrder;
import com.orderWithDataBase.service.AlgoOrderService;
import com.orderWithDataBase.service.TradeService;
import com.orderWithDataBase.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlgoOrderController {

    private static final String STATUS_KEY="status";
    private static final String MESSAGE_KEY="message";
    @Autowired
    UserOrderService userOrderService;

    AlgoOrder algoOrder;

    @Autowired
    TradeService tradeService;

    @Autowired
    AlgoOrderService algoOrderService;

    @Autowired
    JSONObject jsonObject;

    @PostMapping("/placeAlgoOrder")
    public ResponseEntity<Object> placeAlgoOrder(@RequestBody PlaceAlgoOrder algoOrderObj)
    {
        try {
              algoOrder=new AlgoOrder();

              algoOrder.setInstrumentId(algoOrderObj.getInstId());
              algoOrder.setTradeMode(algoOrderObj.getTdMode());
              algoOrder.setMarginCurrency(algoOrderObj.getCcy());
              algoOrder.setOrderSide(algoOrderObj.getSide());
              algoOrder.setPositionSide(algoOrderObj.getPosSide());
              algoOrder.setOrderType(algoOrderObj.getOrdType());
              algoOrder.setQuantity(algoOrderObj.getSz());
              algoOrder.setOrderTag(algoOrderObj.getTag());
              algoOrder.setOrderQuantityUnitSetting(algoOrderObj.getTgtCcy());
              algoOrder.setClientSuppliedAlgoID(algoOrderObj.getAlgoClOrdId());
              algoOrder.setCloseFraction(algoOrderObj.getCloseFraction());


            jsonObject=tradeService.placeAlgoOrder(algoOrderObj);
            // Get the "data" array
            JSONArray dataArray = jsonObject.getJSONArray("data");
            // Get the first object in the array
            JSONObject dataObject = dataArray.getJSONObject(0);
            // Get the value of "sMsg"
            String sMsgValue = dataObject.getString("sMsg");
            //get the value of "ordId"
            String algoOrdId=dataObject.getString("algoId");

            algoOrder.setAlgoOrderId(algoOrdId);
            algoOrderService.placeAlgoOrder(algoOrder);

            jsonObject.put("status",HttpStatus.CREATED);
            return new ResponseEntity<>(jsonObject,HttpStatus.CREATED);
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cancelAlgoOrder")
    public ResponseEntity<Object> cancelAlgoOrder(@RequestBody List<CancelAlgoOrder> cancelAlgoOrderList)
    {
        String msg=algoOrderService.cancelAlgoOrder(cancelAlgoOrderList);

        jsonObject=tradeService.cancelAlgoOrder(cancelAlgoOrderList);
        return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }

    @PostMapping("/amendAlgoOrder")
    public ResponseEntity<Object> amendAlgoOrder(@RequestBody AmendAlgos amendAlgosObj)
    {
        jsonObject=tradeService.amendAlgoOrder(amendAlgosObj);
        return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }
}
