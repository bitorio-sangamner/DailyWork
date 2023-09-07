package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.exception.APIException;
import com.okex.open.api.service.publicData.PublicDataAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.Path;

@RestController
public class PublicDataController {
    @Autowired
    private PublicDataAPIService publicDataAPIService;

    @GetMapping("/public_data/get_instrumentation_details")
    public ResponseEntity<Object> showInstrumentationDetails(@RequestBody JSONObject jsonData) {
        String instType = jsonData.getString("instType");
        String uly = jsonData.getString("uly");
        String instFamily = jsonData.getString("instFamily");
        String instId = jsonData.getString("instId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = publicDataAPIService.getInstruments(instType, uly, instFamily, instId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/public_data/get_delivery_exercise_history")
    public ResponseEntity<Object> getDeliveryExerciseHistory(@RequestBody JSONObject jsonData) {
        String instType = jsonData.getString("instType");
        String uly = jsonData.getString("uly");
        String instFamily = jsonData.getString("instFamily");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = publicDataAPIService.getDeliveryExerciseHistory(instType, uly, instFamily, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/public_data/get_open_interest")
    public ResponseEntity<Object> getOpenInterest(@RequestBody JSONObject jsonData) {
        String instType = jsonData.getString("instType");
        String uly = jsonData.getString("uly");
        String instFamily = jsonData.getString("instFamily");
        String instId = jsonData.getString("instId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = publicDataAPIService.getOpenInterest(instType, uly, instFamily, instId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
    @GetMapping("/public_data/get_funding_rate/{instId}")
    public ResponseEntity<Object> getFundingRate(@PathVariable("instId") String instId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = publicDataAPIService.getFundingRate(instId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/public_data/get_funding_rate_history")
    public ResponseEntity<Object> getFundingRateHistory(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = publicDataAPIService.getFundingRateHistory(instId, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/public_data/get_limit_price/{instId}")
    public ResponseEntity<Object> getLimitPrice(@PathVariable("instId") String instId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = publicDataAPIService.getLimitPrice(instId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/public_data/get_option_market_data")
    public ResponseEntity<Object> getOptionMarketData(@RequestBody JSONObject jsonData) {
        String uly = jsonData.getString("uly");
        String instFamily = jsonData.getString("instFamily");
        String expTime = jsonData.getString("expTime");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = publicDataAPIService.getOptionMarketData(uly, instFamily, expTime);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/public_data/get_estimated_delivery_exercise_price/{instId}")
    public ResponseEntity<Object> getEstimatedDeliveryExercisePrice(@PathVariable("instId") String instId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = publicDataAPIService.getEstimatedDeliveryExcercisePrice(instId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/public_data/get_discounted_rate_and_interest_free_quote")
    public ResponseEntity<Object> getDiscountRateAndInterestFreeQuota(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String discountLv = jsonData.getString("discountLv");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = publicDataAPIService.getDiscountRateAndInterestFreeQuota(currency, discountLv);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/public_data/get_system_time")
    public ResponseEntity<Object> getSystemTime() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = publicDataAPIService.getSystemTime();
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/public_data/get_liquidation_orders")
    public ResponseEntity<Object> getLiquidationOrders(@RequestBody JSONObject jsonData) {
        String instType = jsonData.getString("instType");
        String mgnMode = jsonData.getString("mgnMode");
        String instId = jsonData.getString("instId");
        String ccy = jsonData.getString("ccy");
        String uly = jsonData.getString("uly");
        String instFamily = jsonData.getString("instFamily");
        String alias = jsonData.getString("alias");
        String state = jsonData.getString("state");
        String before = jsonData.getString("before");
        String after = jsonData.getString("after");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = publicDataAPIService.getLiquidationOrders(instType, mgnMode, uly, instId, ccy, instFamily, alias, state, after, before, limit);
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
