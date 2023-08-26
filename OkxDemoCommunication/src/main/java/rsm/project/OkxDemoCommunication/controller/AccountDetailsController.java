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

    @GetMapping("/get_account_currency_balance/{currency}")
    public ResponseEntity<Object> getAccountDetails(@PathVariable String currency) {
        JSONObject jsonData = accountAPIService.getBalance(currency);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @GetMapping("/get_account_configs")
    public ResponseEntity<Object> getAccountConfigs() {
        JSONObject jsonData = accountAPIService.getAccountConfiguration();
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @GetMapping("/get_risk_state")
    public ResponseEntity<Object> getRiskState() {
        JSONObject jsonData = accountAPIService.getRiskState();
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }
    @GetMapping("/get_currency_interest_rate/{currency}")
    public ResponseEntity<Object> getInterestRate(@PathVariable String currency) {
        JSONObject jsonData = accountAPIService.getInterestRate(currency);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @GetMapping("/get_account_greeks/{currency}")
    public ResponseEntity<Object> getAccountGreeks(@PathVariable String currency) {
        JSONObject jsonData = accountAPIService.getAccountGreeks(currency);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }


}
