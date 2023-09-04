package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.account.param.SetLeverage;
import com.okex.open.api.bean.account.param.SetPositionMode;
import com.okex.open.api.bean.account.param.SetTheDisplayTypeOfGreeks;
import com.okex.open.api.exception.APIException;
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
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.getBalance(currency);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/get_account_configs")
    public ResponseEntity<Object> getAccountConfigs() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.getAccountConfiguration();
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/get_risk_state")
    public ResponseEntity<Object> getRiskState() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.getRiskState();
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
    @GetMapping("/get_currency_interest_rate/{currency}")
    public ResponseEntity<Object> getInterestRate(@PathVariable String currency) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.getInterestRate(currency);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/get_account_greeks/{currency}")
    public ResponseEntity<Object> getAccountGreeks(@PathVariable String currency) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.getAccountGreeks(currency);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/get_account_and_positions/{instType}")
    public ResponseEntity<Object> getAccountAndPosition(@PathVariable String instType) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.getAccountAndPosition(instType);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/get_positions")
    public ResponseEntity<Object> getPositions(@RequestBody JSONObject positions) {
        String instType = positions.getString("instType");
        String instId = positions.getString("instId");
        String posId = positions.getString("posId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.getPositions(instType, instId, posId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/set_position_mode")
    public ResponseEntity<Object> setPositionMode(@RequestBody SetPositionMode position) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.setPositionMode(position);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/set_leverage")
    public ResponseEntity<Object> setLeverage(@RequestBody SetLeverage setLeverage) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.setLeverage(setLeverage);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/get_max_size_tradeable_instrument")
    public ResponseEntity<Object> getMaximumTradableSizeForInstrument(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String tdMode = jsonData.getString("tdMode");
        String ccy = jsonData.getString("ccy");
        String px = jsonData.getString("px");
        String leverage = jsonData.getString("leverage");
        boolean unSpotOffset = jsonData.getBoolean("unSpotOffset") != null && jsonData.getBoolean("unSpotOffset");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.getMaximumTradableSizeForInstrument(instId, tdMode, ccy, px, leverage, unSpotOffset);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/get_max_avail_tradeable_amount")
    public ResponseEntity<Object> getMaximumAvailableTradableAmount(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String tdMode = jsonData.getString("tdMode");
        String ccy = jsonData.getString("ccy");
        String px = jsonData.getString("px");
        String quickMgnType = jsonData.getString("quickMgnType");
        boolean unSpotOffset = jsonData.getBoolean("unSpotOffset") != null && jsonData.getBoolean("unSpotOffset");
        boolean reduceOnly = jsonData.getBoolean("reduceOnly") != null && jsonData.getBoolean("reduceOnly");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.getMaximumAvailableTradableAmount(instId, tdMode, ccy, reduceOnly, px, unSpotOffset,quickMgnType);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/get_fee_rates")
    public ResponseEntity<Object> getFeeRates(@RequestBody JSONObject jsonData) {
        String instType = jsonData.getString("instType");
        String instId = jsonData.getString("instId");
        String uly = jsonData.getString("uly");
        String instFamily = jsonData.getString("instFamily");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.getFeeRates(instType, instId, uly, instFamily);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/set_greeks_display")
    public ResponseEntity<Object> setTheDisplayTypeOfGreeks(@RequestBody SetTheDisplayTypeOfGreeks setTheDisplayTypeOfGreeks) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = accountAPIService.setTheDisplayTypeOfGreeks(setTheDisplayTypeOfGreeks);
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
