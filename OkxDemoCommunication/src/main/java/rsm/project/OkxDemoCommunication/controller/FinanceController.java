package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.finance.param.Finance;
import com.okex.open.api.exception.APIException;
import com.okex.open.api.service.finance.FinanceAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FinanceController {

    @Autowired
    FinanceAPIService financeAPIService;

    @GetMapping("/finance/get_balance/{ccy}")
    ResponseEntity<JSONObject> getFinanceBalance(@PathVariable ("ccy") String currency) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = financeAPIService.getFinanceBalance(currency);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/finance/purchase_redempt")
    ResponseEntity<JSONObject> purchaseRedempt(@RequestBody Finance finance) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = financeAPIService.PurchaseRedempt(finance);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/finance/set_lending_rate")
    ResponseEntity<JSONObject> setLendingRate(@RequestBody Finance finance) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = financeAPIService.setLendingRate(finance);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/finance/lending-history")
    ResponseEntity<JSONObject> getLendingHistory(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = financeAPIService.getLendingHistory(currency, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/finance/lending-rate-summary/{ccy}")
    ResponseEntity<JSONObject> getLendingRateSummary(@PathVariable("ccy") String currency) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = financeAPIService.getLendingRateSummary(currency);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/finance/lending-rate-history")
    ResponseEntity<JSONObject> getLendingRateHistory(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = financeAPIService.getLendingRateHistory(currency, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
