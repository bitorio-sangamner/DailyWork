package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.service.blockTrading.BlockTradingAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
