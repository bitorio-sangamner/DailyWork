package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.account.param.SetLeverage;
import com.okex.open.api.bean.account.param.SetPositionMode;
import com.okex.open.api.bean.account.param.SetTheDisplayTypeOfGreeks;
import com.okex.open.api.service.account.AccountAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

@RestController
public class AccountDetailsController {
    @Autowired
    private AccountAPIService accountAPIService;

    @GetMapping("/get_account_currency_balance/{currency}")
    public ResponseEntity<Object> getAccountBalance(@PathVariable String currency) {
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

    @GetMapping("/get_account_and_positions/{instType}")
    public ResponseEntity<Object> getAccountAndPosition(@PathVariable String instType) {
        JSONObject jsonData = accountAPIService.getAccountAndPosition(instType);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @GetMapping("/get_positions")
    public ResponseEntity<Object> getPositions(@RequestBody JSONObject positions) {
        String instType = positions.getString("instType");
        String instId = positions.getString("instId");
        String posId = positions.getString("posId");
        JSONObject jsonData = accountAPIService.getPositions(instType, instId, posId);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @PostMapping("/set_position_mode")
    public ResponseEntity<Object> setPositionMode(@RequestBody SetPositionMode position) {
        JSONObject jsonData = accountAPIService.setPositionMode(position);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @PostMapping("/set_leverage")
    public ResponseEntity<Object> setLeverage(@RequestBody SetLeverage setLeverage) {
        JSONObject jsonData = accountAPIService.setLeverage(setLeverage);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @GetMapping("/get_max_size_tradeable_instrument")
    public ResponseEntity<Object> getMaximumTradableSizeForInstrument(@RequestBody JSONObject jsonObject) {
        String instId = jsonObject.getString("instId");
        String tdMode = jsonObject.getString("tdMode");
        String ccy = jsonObject.getString("ccy");
        String px = jsonObject.getString("px");
        String leverage = jsonObject.getString("leverage");
        boolean unSpotOffset = jsonObject.getBoolean("unSpotOffset") != null && jsonObject.getBoolean("unSpotOffset");

        JSONObject jsonData = accountAPIService.getMaximumTradableSizeForInstrument(instId, tdMode, ccy, px, leverage, unSpotOffset);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @GetMapping("/get_max_avail_tradeable_amount")
    public ResponseEntity<Object> getMaximumAvailableTradableAmount(@RequestBody JSONObject jsonObject) {
        String instId = jsonObject.getString("instId");
        String tdMode = jsonObject.getString("tdMode");
        String ccy = jsonObject.getString("ccy");
        String px = jsonObject.getString("px");
        String quickMgnType = jsonObject.getString("quickMgnType");
        boolean unSpotOffset = jsonObject.getBoolean("unSpotOffset") != null && jsonObject.getBoolean("unSpotOffset");
        boolean reduceOnly = jsonObject.getBoolean("reduceOnly") != null && jsonObject.getBoolean("reduceOnly");

        JSONObject jsonData = accountAPIService.getMaximumAvailableTradableAmount(instId, tdMode, ccy, reduceOnly, px, unSpotOffset,quickMgnType);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @GetMapping("/get_fee_rates")
    public ResponseEntity<Object> getFeeRates(@RequestBody JSONObject jsonObject) {
        String instType = jsonObject.getString("instType");
        String instId = jsonObject.getString("instId");
        String uly = jsonObject.getString("uly");
        String instFamily = jsonObject.getString("instFamily");
        JSONObject jsonData = accountAPIService.getFeeRates(instType, instId, uly, instFamily);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @PostMapping("/set_greeks_display")
    public ResponseEntity<Object> setTheDisplayTypeOfGreeks(@RequestBody SetTheDisplayTypeOfGreeks setTheDisplayTypeOfGreeks) {
        JSONObject jsonData = accountAPIService.setTheDisplayTypeOfGreeks(setTheDisplayTypeOfGreeks);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }
}
