package com.okxRestApi.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.PlaceAlgoOrder;
import com.okex.open.api.exception.APIException;
import com.okxRestApi.service.AlgoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlgoOrderController {

    @Autowired
    AlgoOrderService algoOrderService;

    @Autowired
    JSONObject jsonObject;
    @PostMapping("/placeAlgoOrder")
    public ResponseEntity<Object> placeAlgoOrder(@RequestBody PlaceAlgoOrder algoOrderObj)
    {
        try {
            jsonObject = algoOrderService.placeAlgoOrder(algoOrderObj);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
