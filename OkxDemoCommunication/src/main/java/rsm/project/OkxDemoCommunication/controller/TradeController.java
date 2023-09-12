package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.AmendOrder;
import com.okex.open.api.bean.trade.param.CancelOrder;
import com.okex.open.api.bean.trade.param.ClosePositions;
import com.okex.open.api.bean.trade.param.PlaceOrder;
import com.okex.open.api.exception.APIException;
import com.okex.open.api.service.trade.TradeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeController {

    @Autowired
    private TradeAPIService tradeAPIService;

    @PostMapping("/trade/place_order")
    public ResponseEntity<JSONObject> placeOrder(@RequestBody PlaceOrder placeOrder) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.placeOrder(placeOrder);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/trade/place_multiple_order")
    public ResponseEntity<JSONObject> placeMultipleOrders(@RequestBody List<PlaceOrder> listOfPlaceOrder) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.placeMultipleOrders(listOfPlaceOrder);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/trade/cancel_order")
    public ResponseEntity<JSONObject> cancelOrder(@RequestBody CancelOrder cancelOrder) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.cancelOrder(cancelOrder);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/trade/cancel_multiple_order")
    public ResponseEntity<JSONObject> cancelMultipleOrders(@RequestBody List<CancelOrder> listOfCancelOrder) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.cancelMultipleOrders(listOfCancelOrder);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/trade/amend_order")
    public ResponseEntity<JSONObject> amendOrder(@RequestBody AmendOrder amendOrder) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.amendOrder(amendOrder);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/trade/amend_multiple_order")
    public ResponseEntity<JSONObject> amendMultipleOrders(@RequestBody List<AmendOrder> listOfAmendOrder) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.amendMultipleOrders(listOfAmendOrder);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/trade/close_position")
    public ResponseEntity<JSONObject> closePositions(@RequestBody ClosePositions closePositions) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.closePositions(closePositions);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/trade/get_order_details")
    public ResponseEntity<JSONObject> getOrderDetails(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String ordId = jsonData.getString("ordId");
        String clOrdId = jsonData.getString("clOrdId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.getOrderDetails(instId, ordId, clOrdId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
    @GetMapping("/trade/get_order_history_7_days")
    public ResponseEntity<JSONObject> getOrderHistory7days(@RequestBody JSONObject jsonData) {
        String instType = jsonData.getString("instType");
        String uly = jsonData.getString("uly");
        String instId = jsonData.getString("instId");
        String ordType = jsonData.getString("ordType");
        String instFamily = jsonData.getString("instFamily");
        String state = jsonData.getString("state");
        String category = jsonData.getString("category");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        String begin = jsonData.getString("begin");
        String end = jsonData.getString("end");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.getOrderHistory7days(instType, uly, instId, ordType, instFamily, state, category, after, before, limit, begin, end);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/trade/get_order_history_3_months")
    public ResponseEntity<JSONObject> getOrderHistory3months(@RequestBody JSONObject jsonData) {
        String instType = jsonData.getString("instType");
        String uly = jsonData.getString("uly");
        String instId = jsonData.getString("instId");
        String ordType = jsonData.getString("ordType");
        String instFamily = jsonData.getString("instFamily");
        String state = jsonData.getString("state");
        String category = jsonData.getString("category");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        String begin = jsonData.getString("begin");
        String end = jsonData.getString("end");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.getOrderHistory3months(instType, uly, instId, ordType, instFamily, state, category, after, before, limit, begin, end);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/trade/get_transaction_details")
    public ResponseEntity<JSONObject> getTransactionDetails(@RequestBody JSONObject jsonData) {
        String instType = jsonData.getString("instType");
        String uly = jsonData.getString("uly");
        String instId = jsonData.getString("instId");
        String ordId = jsonData.getString("ordId");
        String instFamily = jsonData.getString("instFamily");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        String begin = jsonData.getString("begin");
        String end = jsonData.getString("end");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.getTransactionDetails(instType, uly, instId, ordId, instFamily, after, before, limit, begin, end);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/trade/get_transaction_details_for_3_months")
    public ResponseEntity<JSONObject> getTransactionDetailsForThreeMonths(@RequestBody JSONObject jsonData) {
        String instType = jsonData.getString("instType");
        String uly = jsonData.getString("uly");
        String instId = jsonData.getString("instId");
        String ordId = jsonData.getString("ordId");
        String instFamily = jsonData.getString("instFamily");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        String begin = jsonData.getString("begin");
        String end = jsonData.getString("end");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = tradeAPIService.getTransactionDetails(instType, uly, instId, ordId, instFamily, after, before, limit, begin, end);
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
