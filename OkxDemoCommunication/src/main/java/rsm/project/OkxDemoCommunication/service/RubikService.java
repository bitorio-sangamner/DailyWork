package rsm.project.OkxDemoCommunication.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.exception.APIException;
import com.okex.open.api.service.rubik.RubikAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RubikService {

    @Autowired
    private RubikAPIService rubikAPIService;

    @GetMapping("/rubik/get_support_coin")
    public ResponseEntity<Object> getSupportCoin() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = rubikAPIService.getSupportCoin();
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rubik/get_taker_volume")
    public ResponseEntity<Object> getTakerVolume(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String instType = jsonData.getString("instType");
        String begin = jsonData.getString("begin");
        String end = jsonData.getString("end");
        String period = jsonData.getString("period");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = rubikAPIService.getTakerVolume(currency, instType, begin, end, period);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rubik/get_loan_ratio")
    public ResponseEntity<Object> getLoanRatio(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String begin = jsonData.getString("begin");
        String end = jsonData.getString("end");
        String period = jsonData.getString("period");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = rubikAPIService.getLoanRatio(currency, begin, end, period);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rubik/get_long_short_account_ratio")
    public ResponseEntity<Object> getLongShortAccountRatio(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String begin = jsonData.getString("begin");
        String end = jsonData.getString("end");
        String period = jsonData.getString("period");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = rubikAPIService.getLongShortAccountRatio(currency, begin, end, period);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rubik/get_open_interest_volume")
    public ResponseEntity<Object> getOpenInterestVolume(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String begin = jsonData.getString("begin");
        String end = jsonData.getString("end");
        String period = jsonData.getString("period");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = rubikAPIService.getOpenInterestVolume(currency, begin, end, period);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rubik/get_option_open_interest_volume")
    public ResponseEntity<Object> getOptionOpenInterestVolume(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String period = jsonData.getString("period");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = rubikAPIService.getOptionOpenInterestVolume(currency, period);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rubik/get_open_interest_volume_ratio")
    public ResponseEntity<Object> getOpenInterestVolumeRatio(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String period = jsonData.getString("period");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = rubikAPIService.getOpenInterestVolumeRatio(currency, period);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rubik/get_open_interest_volume_strike")
    public ResponseEntity<Object> getOpenInterestVolumeStrike(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String expTime = jsonData.getString("expTime");
        String period = jsonData.getString("period");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = rubikAPIService.getOpenInterestVolumeStrike(currency, expTime, period);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rubik/get_open_interest_volume_expiry")
    public ResponseEntity<Object> getOpenInterestVolumeExpiry(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String period = jsonData.getString("period");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = rubikAPIService.getOpenInterestVolumeExpiry(currency, period);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rubik/get_taker_block_volume")
    public ResponseEntity<Object> getTakerBlockVolume(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String period = jsonData.getString("period");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = rubikAPIService.getTakerBlockVolume(currency, period);
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
