package rsm.project.OkxDemoCommunication.controller;

import com.okex.open.api.bean.earn.param.Cancel;
import com.okex.open.api.bean.earn.param.Purchase;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.earn.param.Redeem;
import com.okex.open.api.service.earn.EarnAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EarnController {
    @Autowired
    private EarnAPIService earnAPIService;

    @GetMapping("/earn/get_offers")
    public ResponseEntity<JSONObject> getOffers(@RequestBody JSONObject jsonData) {
        String productId = jsonData.getString("productId");
        String protocolType = jsonData.getString("protocolType");
        String ccy = jsonData.getString("ccy");
        JSONObject jsonObject = earnAPIService.getOffers(productId, protocolType, ccy);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/earn/purchase")
    public ResponseEntity<JSONObject> purchase(@RequestBody Purchase purchase) {
        JSONObject jsonObject = earnAPIService.purchase(purchase);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/earn/redeem")
    public ResponseEntity<JSONObject> redeem(@RequestBody Redeem redeem) {
        JSONObject jsonObject = earnAPIService.redeem(redeem);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/earn/cancel")
    public ResponseEntity<JSONObject> cancel(@RequestBody Cancel cancel) {
        JSONObject jsonObject = earnAPIService.cancel(cancel);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/earn/get_active_orders")
    public ResponseEntity<JSONObject> getActiveOrders(@RequestBody JSONObject jsonData) {
        String productId = jsonData.getString("productId");
        String protocolType = jsonData.getString("protocolType");
        String ccy = jsonData.getString("ccy");
        String state = jsonData.getString("state");
        JSONObject jsonObject = earnAPIService.getActiveOrders(productId, protocolType, ccy, state);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/earn/get_orders_history")
    public ResponseEntity<JSONObject> getHistoryOrders(@RequestBody JSONObject jsonData) {
        String productId = jsonData.getString("productId");
        String protocolType = jsonData.getString("protocolType");
        String ccy = jsonData.getString("ccy");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = earnAPIService.getHistoryOrders(productId, protocolType, ccy, after, before, limit);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
