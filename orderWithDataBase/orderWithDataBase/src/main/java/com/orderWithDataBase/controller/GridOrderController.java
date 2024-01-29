package com.orderWithDataBase.controller;

import com.okex.open.api.bean.gridTrading.param.OrderAlgo;
import com.orderWithDataBase.entities.GridOrder;
import com.orderWithDataBase.service.GridOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GridOrderController {

    @Autowired
    GridOrderService gridOrderService;

    @Autowired
    GridOrder gridOrder;

    @PostMapping("/saveGridOrder")
    public ResponseEntity<Object> saveGridOrder(@RequestBody OrderAlgo orderAlgoObj)
    {
       gridOrder=new GridOrder();

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

        GridOrder savedGridObj=gridOrderService.saveGridOrder(gridOrder);

        return new ResponseEntity<>(savedGridObj, HttpStatus.CREATED);
    }

}
