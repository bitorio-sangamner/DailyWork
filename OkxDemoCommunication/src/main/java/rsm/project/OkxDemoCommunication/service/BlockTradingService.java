package rsm.project.OkxDemoCommunication.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.blockTrading.param.*;
import com.okex.open.api.service.blockTrading.BlockTradingAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockTradingService {
    @Autowired
    private BlockTradingAPIService blockTradingAPIService;

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


    @GetMapping("/get_rfqs")
    public ResponseEntity<Object> getRfqs(@RequestBody JSONObject jsonObject) {
        String rfqId = jsonObject.getString("rfqId");
        String clRfqId = jsonObject.getString("clRfqId");
        String state = jsonObject.getString("state");
        String beginId = jsonObject.getString("beginId");
        String endId = jsonObject.getString("endId");
        String limit = jsonObject.getString("limit");
        JSONObject jsonData = blockTradingAPIService.getRfqs(rfqId, clRfqId, state, beginId, endId, limit);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @GetMapping("/get_quotes")
    public ResponseEntity<Object> getQuotes(@RequestBody JSONObject jsonObject) {
        String rfqId = jsonObject.getString("rfqId");
        String clRfqId = jsonObject.getString("clRfqId");
        String quoteId = jsonObject.getString("quoteId");
        String clQuoteId = jsonObject.getString("clQuoteId");
        String state = jsonObject.getString("state");
        String beginId = jsonObject.getString("beginId");
        String endId = jsonObject.getString("endId");
        String limit = jsonObject.getString("limit");
        JSONObject jsonData = blockTradingAPIService.getQuotes(rfqId, clRfqId, quoteId, clQuoteId, state, beginId, endId, limit);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @GetMapping("/get_trades")
    public ResponseEntity<Object> getTrades(@RequestBody JSONObject jsonObject) {
        String rfqId = jsonObject.getString("rfqId");
        String clRfqId = jsonObject.getString("clRfqId");
        String quoteId = jsonObject.getString("quoteId");
        String clQuoteId = jsonObject.getString("clQuoteId");
        String state = jsonObject.getString("state");
        String beginId = jsonObject.getString("beginId");
        String endId = jsonObject.getString("endId");
        String limit = jsonObject.getString("limit");
        String beginTs = jsonObject.getString("beginTs");
        String endTs = jsonObject.getString("endTs");
        JSONObject jsonData = blockTradingAPIService.getTrades(rfqId, clRfqId, quoteId, clQuoteId, state, beginId, endId, limit, beginTs, endTs);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }
}
