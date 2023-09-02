package com.OkxConvertService.controller;

import com.OkxConvertService.service.ConvertService;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.convert.param.EstimateQuote;
import com.okex.open.api.bean.convert.param.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ConvertController
{
    @Autowired
    private ConvertService convertService;


    @GetMapping("/getCurrencies")
    public JSONObject getCurrencies()
    {
        JSONObject json=convertService.getCurrencies();
        return json;
    }

    @GetMapping("/getCurrencyPair")
    public ResponseEntity<Object> getCurrencyPair(@RequestBody JSONObject jsonObject)
    {
        String fromCcy = jsonObject.getString("fromCcy");
        String toCcy = jsonObject.getString("toCcy");

        JSONObject json=convertService.getCurrencyPair(fromCcy,toCcy);
        return ResponseEntity.of(Optional.of(json));

    }

    @PostMapping("/estimateQuote")
    public ResponseEntity<Object> estimateQuote(@RequestBody EstimateQuote estimateQuote)
    {
        JSONObject json=convertService.estimateQuote(estimateQuote);
        return ResponseEntity.of(Optional.of(json));
    }

    @PostMapping("/trade")
    public ResponseEntity<Object> trade(@RequestBody Trade tradeObj)
    {
        JSONObject json=convertService.trade(tradeObj);
        return ResponseEntity.of(Optional.of(json));
    }

    @GetMapping("/getHistory")
    public ResponseEntity<Object> getHistory()
    {
        JSONObject json=convertService.getHistory();
        return ResponseEntity.of(Optional.of(json));
    }

    @GetMapping("/currentSubpositions/{instId}")
    public ResponseEntity<Object> currentSubpositions(@PathVariable String instId)
    {
        JSONObject json=convertService.currentSubpositions(instId);
        return ResponseEntity.of(Optional.of(json));
    }

    @GetMapping("/getInstruments")
    public ResponseEntity<Object> getInstruments()
    {
        JSONObject json=convertService.getInstruments();
        return ResponseEntity.of(Optional.of(json));
    }
}
