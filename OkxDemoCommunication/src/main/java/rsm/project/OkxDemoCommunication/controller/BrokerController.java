package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.service.broker.BrokerAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrokerController {
    @Autowired
    private BrokerAPIService brokerAPIService;

    @GetMapping("/get_info")
    public ResponseEntity<JSONObject> getInfo() {
        JSONObject jsonObject = brokerAPIService.getInfo();
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/get_sub_account_info")
    public ResponseEntity<JSONObject> getSubAccountInfo(@RequestBody JSONObject jsonData) {
        String subAcct = jsonData.getString("subAcct");
        String page = jsonData.getString("d");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = brokerAPIService.getSubAccountInfo(subAcct, page, limit);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
