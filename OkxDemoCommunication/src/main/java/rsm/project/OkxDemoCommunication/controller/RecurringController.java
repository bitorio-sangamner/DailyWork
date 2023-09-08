package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.recurringTrading.Recurring;
import com.okex.open.api.bean.recurringTrading.RecurringAlgoOrder;
import com.okex.open.api.exception.APIException;
import com.okex.open.api.service.recurring.RecurringAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecurringController {
    @Autowired
    RecurringAPIService recurringAPIService;

    @PostMapping("/recurring/place_order_algo")
    public ResponseEntity<Object> placeOrderAlgo(@RequestBody RecurringAlgoOrder recurringAlgoOrder) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = recurringAPIService.placeOrderAlgo(recurringAlgoOrder);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/recurring/amend_order_algo")
    public ResponseEntity<Object> amendOrderAlgo(@RequestBody Recurring recurring) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = recurringAPIService.amendOrderAlgo(recurring);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/recurring/stop_order_algo")
    public ResponseEntity<Object> stopOrderAlgo(@RequestBody List<Recurring> list) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = recurringAPIService.stopOrderAlgo(list);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/recurring/get_order_algo_pending")
    public ResponseEntity<Object> getOrderAlgoPending(@RequestBody JSONObject jsonData) {
        String algoId = jsonData.getString("algoId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = recurringAPIService.getOrderAlgoPending(algoId, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/recurring/get_order_algo_history")
    public ResponseEntity<Object> getOrderAlgoHistory(@RequestBody JSONObject jsonData) {
        String algoId = jsonData.getString("algoId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = recurringAPIService.getOrderAlgoHistory(algoId, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/recurring/get_order_algo_details/{algoId}")
    public ResponseEntity<Object> getOrderAlgoDetails(@PathVariable("algoId") String algoId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = recurringAPIService.getOrderAlgoDetails(algoId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/recurring/get_sub_orders")
    public ResponseEntity<Object> getSubOrders(@RequestBody JSONObject jsonData) {
        String algoId = jsonData.getString("algoId");
        String orderId = jsonData.getString("orderId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = recurringAPIService.getSubOrders(algoId, orderId, after, before, limit);
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
