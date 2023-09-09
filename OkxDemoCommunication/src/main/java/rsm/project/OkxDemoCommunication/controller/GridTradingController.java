package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.gridTrading.param.AmendOrderAlgo;
import com.okex.open.api.bean.gridTrading.param.OrderAlgo;
import com.okex.open.api.bean.gridTrading.param.StopOrderAlgo;
import com.okex.open.api.bean.gridTrading.param.WithdrawIncome;
import com.okex.open.api.exception.APIException;
import com.okex.open.api.service.gridTrading.GridTradingAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GridTradingController {

    @Autowired
    private GridTradingAPIService gridTradingAPIService;

    @PostMapping("/grid_trading/order_algo")
    public ResponseEntity<JSONObject> orderAlgo(@RequestBody OrderAlgo orderAlgo) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = gridTradingAPIService.orderAlgo(orderAlgo);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/grid_trading/amend_order_algo")
    public ResponseEntity<JSONObject> amendOrderAlgo(@RequestBody AmendOrderAlgo amendOrderAlgo) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = gridTradingAPIService.amendOrderAlgo(amendOrderAlgo);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/grid_trading/stop_order_algo")
    public ResponseEntity<JSONObject> stopOrderAlgo(@RequestBody StopOrderAlgo stopOrderAlgo) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = gridTradingAPIService.stopOrderAlgo(stopOrderAlgo);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/grid_trading/history_order_algo")
    public ResponseEntity<JSONObject> getGridAlgoOrderList(@RequestBody JSONObject jsonData) {
        String algoOrdType = jsonData.getString("algoOrdType");
        String algoId = jsonData.getString("algoId");
        String instId = jsonData.getString("instId");
        String instType = jsonData.getString("instType");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = gridTradingAPIService.getGridAlgoOrderList(algoOrdType, algoId, instId, instType, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/grid_trading/details_order_algo")
    public ResponseEntity<JSONObject> getOrdersAlgoDetails(@RequestBody JSONObject jsonData) {
        String algoOrdType = jsonData.getString("algoOrdType");
        String algoId = jsonData.getString("algoId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = gridTradingAPIService.getOrdersAlgoDetails(algoOrdType, algoId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/grid_trading/sub_orders")
    public ResponseEntity<JSONObject> getSubOrders(@RequestBody JSONObject jsonData) {
        String algoOrdType = jsonData.getString("algoOrdType");
        String algoId = jsonData.getString("algoId");
        String type = jsonData.getString("type");
        String groupId = jsonData.getString("groupId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = gridTradingAPIService.getSubOrders(algoOrdType, algoId, type, groupId, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/grid_trading/get_positions")
    public ResponseEntity<JSONObject> getPositions(@RequestBody JSONObject jsonData) {
        String algoOrdType = jsonData.getString("algoOrdType");
        String algoId = jsonData.getString("algoId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = gridTradingAPIService.getPositions(algoOrdType, algoId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/grid_trading/withdrawal_income")
    public ResponseEntity<JSONObject> withdrawIncome(@RequestBody WithdrawIncome withdrawIncome) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = gridTradingAPIService.withdrawIncome(withdrawIncome);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/grid_trading/grid_test")
    public ResponseEntity<JSONObject> getGridTest(@RequestBody JSONObject jsonData) {
        String algoOrdType = jsonData.getString("algoOrdType");
        String instId = jsonData.getString("instId");
        String direction = jsonData.getString("direction");
        String duration = jsonData.getString("duration");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = gridTradingAPIService.getGridTest(algoOrdType, instId, direction, duration);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/grid_trading/margin_balance")
    public ResponseEntity<JSONObject> marginBalance(@RequestBody WithdrawIncome withdrawIncome) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = gridTradingAPIService.marginBalance(withdrawIncome);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/grid_trading/compute_margin_balance")
    public ResponseEntity<JSONObject> computeMarginBalance(@RequestBody WithdrawIncome withdrawIncome) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = gridTradingAPIService.computeMarginBalance(withdrawIncome);
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

