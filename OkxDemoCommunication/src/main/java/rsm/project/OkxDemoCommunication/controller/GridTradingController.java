package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.gridTrading.param.OrderAlgo;
import com.okex.open.api.exception.APIException;
import com.okex.open.api.service.gridTrading.GridTradingAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GridTradingController {

    @Autowired
    GridTradingAPIService gridTradingAPIService;

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
}
