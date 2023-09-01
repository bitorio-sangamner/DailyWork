package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.convert.param.EstimateQuote;
import com.okex.open.api.bean.convert.param.Trade;
import com.okex.open.api.service.convert.ConvertAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConvertController {
    @Autowired
    ConvertAPIService convertAPIService;

    @GetMapping("/get_convert_currencies")
    public ResponseEntity<JSONObject> getConvertCurrencies() {
        JSONObject jsonObject = convertAPIService.getCurrencies();
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/get_currency_pair")
    public ResponseEntity<JSONObject> getCurrencyPair(@RequestBody JSONObject jsonData) {
        String fromCcy = jsonData.getString("fromCcy");
        String toCcy = jsonData.getString("toCcy");
        JSONObject jsonObject = convertAPIService.getCurrencyPair(fromCcy, toCcy);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/get_convert_history")
    public ResponseEntity<JSONObject> getHistory(@RequestBody JSONObject jsonData) {
        String before = jsonData.getString("before");
        String after = jsonData.getString("after");
        String limit = jsonData.getString("limit");
        String tag = jsonData.getString("tag");
        JSONObject jsonObject = convertAPIService.getHistory(after, before, limit, tag);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/convert_trade")
    public ResponseEntity<JSONObject> trade(@RequestBody Trade trade) {
        JSONObject jsonObject = convertAPIService.trade(trade);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/estimate_quote")
    public ResponseEntity<JSONObject> estimateQuote(@RequestBody EstimateQuote estimateQuote) {
        JSONObject jsonObject = convertAPIService.estimateQuote(estimateQuote);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
