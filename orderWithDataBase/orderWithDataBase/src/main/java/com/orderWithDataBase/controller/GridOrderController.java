package com.orderWithDataBase.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.gridTrading.param.OrderAlgo;
import com.okex.open.api.exception.APIException;
import com.orderWithDataBase.entities.GridOrder;
import com.orderWithDataBase.service.GridOrderService;
import com.orderWithDataBase.service.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GridOrderController {

    private static final String STATUS_KEY="status";
    private static final String MESSAGE_KEY="message";
    @Autowired
    GridOrderService gridOrderService;


    GridOrder gridOrder;

    @Autowired
    GridService gridService;

    @Autowired
    JSONObject jsonObject;

    @PostMapping("/saveGridOrder")
    public ResponseEntity<Object> saveGridOrder(@RequestBody OrderAlgo orderAlgoObj)
    {
        try {

            gridOrder = new GridOrder();

            gridOrder.setGridNum(orderAlgoObj.getGridNum());
            gridOrder.setInstId(orderAlgoObj.getInstId());
            gridOrder.setAlgoOrdType(orderAlgoObj.getAlgoOrdType());
            gridOrder.setMaxPx(orderAlgoObj.getMaxPx());
            gridOrder.setMinPx(orderAlgoObj.getMinPx());
            gridOrder.setRunType(orderAlgoObj.getRunType());
            gridOrder.setTpTriggerPx(orderAlgoObj.getTpTriggerPx());
            gridOrder.setSlTriggerPx(orderAlgoObj.getSlTriggerPx());
            gridOrder.setTag(orderAlgoObj.getTag());
            gridOrder.setQuoteSz(orderAlgoObj.getQuoteSz());
            gridOrder.setBaseSz(orderAlgoObj.getBaseSz());
            gridOrder.setSz(orderAlgoObj.getSz());
            gridOrder.setDirection(orderAlgoObj.getDirection());
            gridOrder.setLever(orderAlgoObj.getLever());
            gridOrder.setBasePos(orderAlgoObj.getBasePos());

            jsonObject = gridService.placeGridOrderOnOkx(orderAlgoObj);

            JSONArray dataArray = jsonObject.getJSONArray("data");
            JSONObject jsonObj = dataArray.getJSONObject(0);
            String algoId = jsonObj.getString("algoId");

            if (algoId != null && !algoId.equals("") && !algoId.equals(" ")) {
                gridOrder.setAlgoId(algoId);
                GridOrder savedGridObj = gridOrderService.saveGridOrder(gridOrder);

                jsonObject.put("status", HttpStatus.CREATED);
                return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
            }
        }//try
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            jsonObject.put("status",HttpStatus.NOT_ACCEPTABLE);
            return new ResponseEntity<>(jsonObject,HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getGridOrderDetails")
    public ResponseEntity<Object> getGridOrderDetails(@RequestBody JSONObject json)
    {
        String algoOrdType=json.getString("algoOrdType");
        String algoId=json.getString("algoId");

       jsonObject=gridService.getGridOrderDetailsFromOkx(algoOrdType,algoId);

       if(jsonObject.getString("msg").equals("")) {
           jsonObject.put(STATUS_KEY,HttpStatus.FOUND);
           return new ResponseEntity<>(jsonObject, HttpStatus.FOUND);
       }

        jsonObject.put(STATUS_KEY,HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(jsonObject, HttpStatus.NOT_ACCEPTABLE);
    }
}
