package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.service.account.AccountAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountDetailsController {
    @Autowired
    private AccountAPIService accountAPIService;

    @GetMapping("/display_account_details")
    public ResponseEntity<Object> getAccountDetails() {
        JSONObject jsonData = accountAPIService.getBalance("BTC");
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @GetMapping("/display_account_configs")
    public ResponseEntity<Object> getAccountConfigs() {
        JSONObject jsonData = accountAPIService.getAccountConfiguration();
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }
}
