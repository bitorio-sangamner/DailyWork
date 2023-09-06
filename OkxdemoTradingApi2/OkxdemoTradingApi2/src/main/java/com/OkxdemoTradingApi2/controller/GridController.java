package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.GridService;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.gridTrading.param.AmendOrderAlgo;
import com.okex.open.api.bean.gridTrading.param.OrderAlgo;
import com.okex.open.api.bean.gridTrading.param.StopOrderAlgo;
import com.okex.open.api.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class GridController
{
    @Autowired
    public GridService gridService;

    @PostMapping("/orderAlgo")
    public ResponseEntity<Object> orderAlgo(@Valid @RequestBody OrderAlgo orderAlgoObj, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JSONObject json=new JSONObject();

        try
        {
            json=gridService.orderAlgo(orderAlgoObj);
        }

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @PostMapping("/amendOrderAlgo")
    public ResponseEntity<Object> amendOrderAlgo(@Valid @RequestBody AmendOrderAlgo amendOrderAlgo,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JSONObject json=new JSONObject();

        try
        {
            json=gridService.amendOrderAlgo(amendOrderAlgo);
        }

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }

    @PostMapping("/stopOrderAlgo")
    public ResponseEntity stopOrderAlgo(@Valid @RequestBody StopOrderAlgo stopOrderAlgoObj,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JSONObject json=new JSONObject();

        try
        {
            json=gridService.stopOrderAlgo(stopOrderAlgoObj);
        }

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/getGridAlgoOrderList")
    public ResponseEntity getGridAlgoOrderList(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try {
            String algoOrdType = jsonObject.getString("algoOrdType");
            String algoId = jsonObject.getString("algoId");
            String instId = jsonObject.getString("instId");
            String instType = jsonObject.getString("instType");
            String after = jsonObject.getString("after");
            String before = jsonObject.getString("before");
            String limit = jsonObject.getString("limit");

            json=gridService.getGridAlgoOrderList(algoOrdType,algoId,instId,instType,after,before,limit);

        }

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/getGridAlgoOrderHistory")
    public ResponseEntity<Object> getGridAlgoOrderHistory(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try
        {
            String algoOrdType=jsonObject.getString("algoOrdType");
            String algoId=jsonObject.getString("algoId");
            String instId=jsonObject.getString("instId");
            String instType=jsonObject.getString("instType");
            String after=jsonObject.getString("after");
            String befor=jsonObject.getString("befor");
            String limit=jsonObject.getString("limit");

            json=gridService.getGridAlgoOrderHistory(algoOrdType,algoId,instId,instType,after,befor,limit);
        }
        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/getOrdersAlgoDetails")
    public ResponseEntity<Object> getOrdersAlgoDetails(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try
        {
            String algoOrdType=jsonObject.getString("algoOrdType");
            String algoId=jsonObject.getString("algoId");

            json=gridService.getOrdersAlgoDetails(algoOrdType,algoId);
        }

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/getSubOrder")
    public ResponseEntity<Object> getSubOrders(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try {
            String algoOrdType = jsonObject.getString("algoOrdType");
            String algoId = jsonObject.getString("algoId");
            String type = jsonObject.getString("type");
            String groupId = jsonObject.getString("groupId");
            String after = jsonObject.getString("after");
            String before = jsonObject.getString("before");
            String limit = jsonObject.getString("limit");

            json=gridService.getSubOrders(algoOrdType,algoId,type,groupId,after,before,limit);

        }

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/getGridPositions")
    public ResponseEntity<Object> getPositions(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try
        {
            String algoOrdType=jsonObject.getString("algoOrdType");
            String algoId=jsonObject.getString("algoId");

            json=gridService.getPositions(algoOrdType,algoId);
        }

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json,HttpStatus.OK);
    }
}
