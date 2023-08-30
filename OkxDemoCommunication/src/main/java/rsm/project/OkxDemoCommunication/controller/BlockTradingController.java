package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.blockTrading.param.*;
import com.okex.open.api.service.blockTrading.BlockTradingAPIService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockTradingController {
    @Autowired
    BlockTradingAPIService blockTradingAPIService;

    @GetMapping("/get_counterparties")
    public ResponseEntity<Object> getCounterparties() {
        JSONObject jsonData = blockTradingAPIService.getCounterparties();
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @GetMapping("/get_public_trades")
    public ResponseEntity<Object> getPublicTrades(@RequestBody JSONObject jsonObject) {
        String beginId = jsonObject.getString("beginId");
        String endId = jsonObject.getString("endId");
        String limit = jsonObject.getString("limit");
        JSONObject jsonData = blockTradingAPIService.getPublicTrades(beginId, endId, limit);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @PostMapping("/create_rfq")
    public ResponseEntity<Object> createRfq(@RequestBody CreateRfq createRfq) {
        JSONObject jsonData = blockTradingAPIService.createRfq(createRfq);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @PostMapping("/cancel_rfq")
    public ResponseEntity<Object> cancelRfq(@RequestBody CancelRfq cancleRfq) {
        JSONObject jsonData = blockTradingAPIService.cancelRfq(cancleRfq);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }


    @PostMapping("/cancel_batch_rfqs")
    public ResponseEntity<Object> cancelBatchRfqs(@RequestBody CancelBatchRfqs cancelBatchRfqs) {
        JSONObject jsonData = blockTradingAPIService.cancelBatchRfqs(cancelBatchRfqs);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }


    @PostMapping("/cancel_all_rfqs")
    public ResponseEntity<Object> cancelAllRfqs() {
        JSONObject jsonData = blockTradingAPIService.cancelAllRfqs();
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @PostMapping("/create_quote")
    public ResponseEntity<Object> createQuote(@RequestBody CreateQuote createQuote) {
        JSONObject jsonData = blockTradingAPIService.createQuote(createQuote);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @PostMapping("/cancel_quote")
    public ResponseEntity<Object> cancelQuote(@RequestBody CancelQuote cancelQuote) {
        JSONObject jsonData = blockTradingAPIService.cancelQuote(cancelQuote);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @PostMapping("/execute_quote")
    public ResponseEntity<Object> executeQuote(@RequestBody ExecuteQuote executeQuote) {
        JSONObject jsonData = blockTradingAPIService.executeQuote(executeQuote);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }
}
