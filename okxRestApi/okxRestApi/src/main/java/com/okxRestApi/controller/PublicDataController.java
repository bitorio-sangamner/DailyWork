package com.okxRestApi.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.exception.APIException;
import com.okxRestApi.service.PublicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicDataController {

    @Autowired
    JSONObject jsonObject;
    @Autowired
    PublicDataService publicDataService;
    @GetMapping("/getInstrumentData")
    public ResponseEntity<Object> getFutureInstrumentData(@RequestBody JSONObject json)
    {
        try {
            String instrumentType=json.getString("instType");
            jsonObject = publicDataService.getInstrumentData(instrumentType);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
        catch(APIException e)
        {
            jsonObject.put("message",e.getMessage());
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getSpotInstrumentData")
    public ResponseEntity<Object> getSpotInstrumentData()
    {
        jsonObject=publicDataService.getSpotInstrumentData();
        return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }
}
