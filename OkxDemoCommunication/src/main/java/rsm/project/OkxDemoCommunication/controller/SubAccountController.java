package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.subAccount.param.SetSubAccountLoanAllocation;
import com.okex.open.api.bean.subAccount.param.SetTransferOut;
import com.okex.open.api.bean.subAccount.param.SubAccountTransfer;
import com.okex.open.api.exception.APIException;
import com.okex.open.api.service.subAccount.SubAccountAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class SubAccountController {

    @Autowired
    private SubAccountAPIService subAccountAPIService;

    @GetMapping("/sub_account/get_list")
    public ResponseEntity<Object> getSubAccountList(@RequestBody JSONObject jsonData) {
        String enable = jsonData.getString("enable");
        String subAcct = jsonData.getString("subAcct");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = subAccountAPIService.getSubAccountList(enable, subAcct, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/sub_account/get_balance/{subAcct}")
    public ResponseEntity<Object> getSubAccountBalances(@PathVariable("subAcct") String subAcct) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = subAccountAPIService.getSubAccountBalances(subAcct);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/sub_account/get_bills")
    public ResponseEntity<Object> getSubAccountBills(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String type = jsonData.getString("type");
        String subAcct = jsonData.getString("subAcct");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = subAccountAPIService.getSubAccountBills(currency, type, subAcct, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/sub_account/transfer")
    public ResponseEntity<Object> subAccountTransfer(@RequestBody SubAccountTransfer subAccountTransfer) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = subAccountAPIService.subAccountTransfer(subAccountTransfer);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/sub_account/set_transfer_out")
    public ResponseEntity<Object> setTransferOut(@RequestBody SetTransferOut setTransferOut) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = subAccountAPIService.setTransferOut(setTransferOut);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/sub_account/get_entrust_subList/{subAcct}")
    public ResponseEntity<Object> getEntrustSubList(@PathVariable("subAcct") String subAcct) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = subAccountAPIService.getEntrustSubList(subAcct);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/sub_account/get_balance_for_currency")
    public ResponseEntity<Object> getSubaccountBalances(@RequestBody JSONObject jsonData) {
        String subAcct = jsonData.getString("subAcct");
        String currency = jsonData.getString("ccy");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = subAccountAPIService.getSubaccountBalances(subAcct, currency);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/sub_account/modify_sub_api_key")
    public ResponseEntity<Object> modifySubApikey(@RequestBody SetTransferOut setTransferOut) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = subAccountAPIService.modifySubApikey(setTransferOut);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/sub_account/if_rebate/{apiKey}")
    public ResponseEntity<Object> ifRebate(@PathVariable("apiKey") String apiKey) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = subAccountAPIService.ifRebate(apiKey);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/sub_account/set_loan_allocation")
    public ResponseEntity<Object> setSubAccountLoanAllocation(@RequestBody SetSubAccountLoanAllocation setSubAccountLoanAllocation) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = subAccountAPIService.setSubAccountLoanAllocation(setSubAccountLoanAllocation);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/sub_account/get_interest_limits")
    public ResponseEntity<Object> getSubAccountInterestLimits(@RequestBody JSONObject jsonData) {
        String subAcct = jsonData.getString("subAcct");
        String currency = jsonData.getString("ccy");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = subAccountAPIService.getSubAccountInterestLimits(subAcct, currency);
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
