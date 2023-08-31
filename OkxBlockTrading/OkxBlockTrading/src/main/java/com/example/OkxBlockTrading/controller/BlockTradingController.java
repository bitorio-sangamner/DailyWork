package com.example.OkxBlockTrading.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.OkxBlockTrading.service.BlockTradingService;
import com.okex.open.api.bean.blockTrading.param.CancelRfq;
import com.okex.open.api.bean.blockTrading.param.CreateQuote;
import com.okex.open.api.bean.blockTrading.param.CreateRfq;
import com.okex.open.api.bean.blockTrading.param.ExecuteQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlockTradingController
{
    @Autowired
    public BlockTradingService blockTradingService;

    @GetMapping("/getCounterparties")
    public JSONObject getCounterparties()
    {
        JSONObject json=blockTradingService.getCounterparties();
        return json;
    }

    @PostMapping("/createRfq")
    public JSONObject createRfq(@RequestBody CreateRfq createRfq)
    {
        JSONObject json=blockTradingService.createRfq(createRfq);
        return json;
    }

    @DeleteMapping("/cancleRFQ")
    public JSONObject cancleRFQ(@RequestBody CancelRfq cancleRfq)
    {
        JSONObject json=blockTradingService.cancleRFQ(cancleRfq);
        return json;
    }

    @GetMapping("/getPublicTrade")
    public JSONObject getPublicTrage()
    {
        JSONObject json=blockTradingService.getPublicTrades();
        return json;
    }

    @GetMapping("/getMakerInstrumentSettings")
    public JSONObject getMakerInstrumentSettings()
    {
        JSONObject json=blockTradingService.getMakerInstrumentSettings();
        return json;
    }

    @PutMapping("/resetMMPStatus")
    public JSONObject resetMMPStatus()
    {
        JSONObject json=blockTradingService.resetMMPStatus();
        return json;
    }



    @GetMapping("/getQuotes")
    public JSONObject getQuotes()
    {
        JSONObject json=blockTradingService.getQuotes();
        return json;
    }

    @PostMapping("/executeQuote")
    public JSONObject executeQuote(@RequestBody ExecuteQuote executeQuote)
    {
        JSONObject json=blockTradingService.executeQuote(executeQuote);
        return json;
    }

    @GetMapping("/getRfqs")
    public JSONObject getRfqs()
    {
        JSONObject json=blockTradingService.getRfqs();
        return json;
    }

    @GetMapping("/getTrades")
    public JSONObject getTrades()
    {
        JSONObject json=blockTradingService.getTrades();
        return json;
    }

    @PostMapping("/createQuote")
    public JSONObject createQuote(@RequestBody CreateQuote createQuote)
    {
        JSONObject json=blockTradingService.createQuote(createQuote);
        return json;
    }

    @PostMapping("/cancelAllRfqs")
    public JSONObject cancelAllRfqs()
    {
        JSONObject json=blockTradingService.cancelAllRfqs();
        return json;
    }


}
