package rsm.project.OkxDemoCommunication.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.funding.param.*;
import com.okex.open.api.exception.APIException;
import com.okex.open.api.service.funding.FundingAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FundingService {
    @Autowired
    private FundingAPIService fundingAPIService;

    @GetMapping("/funding/get_currencies")
    public ResponseEntity<JSONObject> getCurrencies() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.getCurrencies();
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/get_currency_balance/{ccy}")
    public ResponseEntity<JSONObject> getBalance(@PathVariable("ccy") String currency) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.getBalance(currency);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/funding/funds_transfer")
    public ResponseEntity<JSONObject> fundsTransfer(@RequestBody FundsTransfer fundsTransfer) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.fundsTransfer(fundsTransfer);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/assets_bills_details")
    public ResponseEntity<JSONObject> assetBillsDetails(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String type = jsonData.getString("type");
        String clientId = jsonData.getString("clientId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.assetBillsDetails(currency, limit, clientId, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/get_deposit_address/{ccy}")
    public ResponseEntity<JSONObject> getDepositAddress(@PathVariable("ccy") String currency) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.getDepositAddress(currency);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/get_deposit_history")
    public ResponseEntity<JSONObject> getDepositHistory(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String txId = jsonData.getString("txId");
        String fromWdId = jsonData.getString("fromWdId");
        String type = jsonData.getString("type");
        String state = jsonData.getString("state");
        String limit = jsonData.getString("limit");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String depId = jsonData.getString("depId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.getDepositHistory(currency, txId, fromWdId, type, state, after, before, limit, depId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/funding/withdrawal")
    public ResponseEntity<JSONObject> withdrawal(@RequestBody Withdrawal withdrawal) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.Withdrawal(withdrawal);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/get_withdrawal_history")
    public ResponseEntity<JSONObject> getWithdrawalHistory(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String txId = jsonData.getString("txId");
        String clientId = jsonData.getString("clientId");
        String type = jsonData.getString("type");
        String state = jsonData.getString("state");
        String limit = jsonData.getString("limit");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String wdId = jsonData.getString("wdId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.getWithdrawalHistory(currency, clientId, txId, type, state, after, before, limit, wdId);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/get_deposit_withdrawal_status")
    public ResponseEntity<JSONObject> getDepositWithdrawalStatus(@RequestBody JSONObject jsonData) {
        String wdId = jsonData.getString("wdId");
        String txId = jsonData.getString("txId");
        String ccy = jsonData.getString("ccy");
        String to = jsonData.getString("to");
        String chain = jsonData.getString("chain");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.getDepositWithdrawalStatus(wdId, txId, ccy, to, chain);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/funding/purchase_redempt")
    public ResponseEntity<JSONObject> piggyBankPurchaseRedemption(@RequestBody PiggyBankPurchaseRedemption piggyBankPurchaseRedemption) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.piggyBankPurchaseRedemption(piggyBankPurchaseRedemption);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/savings_balance/{ccy}")
    public ResponseEntity<JSONObject> piggyBalance(@PathVariable("ccy") String currency) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.piggyBalance(currency);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/deposit_lightning")
    public ResponseEntity<JSONObject> depositLightning(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String amt = jsonData.getString("amt");
        String to = jsonData.getString("to");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.depositLightning(currency, amt, to);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/funding/withdrawal_lightning")
    public ResponseEntity<JSONObject> withdrawalLightning(@RequestBody Withdrawal withdrawal) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.Withdrawal(withdrawal);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/transfer_state")
    public ResponseEntity<JSONObject> transferState(@RequestBody JSONObject jsonData) {
        String transId = jsonData.getString("transId");
        String clientId = jsonData.getString("clientId");
        String type = jsonData.getString("type");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.transferState(transId, clientId, type);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/get_asset_valuation/{ccy}")
    public ResponseEntity<JSONObject> assetValuation(@PathVariable("ccy") String currency) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.assetValuation(currency);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/funding/set_lending_rate")
    public ResponseEntity<JSONObject> setLendingRate(@RequestBody SetLendingRate setLendingRate) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.SetLendingRate(setLendingRate);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/get_lending_history")
    public ResponseEntity<JSONObject> getLendingHistory(@RequestBody JSONObject jsonData) {
        String ccy = jsonData.getString("ccy");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.lendingHistory(ccy, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/get_lending_rate_summary/{ccy}")
    public ResponseEntity<JSONObject> getLendingRateSummary(@PathVariable("ccy") String currency) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.lendingRateSummary(currency);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/get_lending_rate_history")
    public ResponseEntity<JSONObject> getLendingRateHistory(@RequestBody JSONObject jsonData) {
        String currency = jsonData.getString("ccy");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.lendingRateHistory(currency, after, before, limit);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/funding/convert_dust_assets")
    public ResponseEntity<JSONObject> convertDustAssets(@RequestBody ConvertDustAssets convertDustAssets) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.convertDustAssets(convertDustAssets);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/funding/cancel_withdrawal")
    public ResponseEntity<JSONObject> cancelWithdrawal(@RequestBody Withdrawal withdrawal) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.Withdrawal(withdrawal);
        } catch (APIException e) {
            String[] keyValuePair = e.getMessage().split(" : ");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("Error Code", keyValuePair[0]);
            jsonObject1.put("Message", keyValuePair[1]);
            return new ResponseEntity<>(jsonObject1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/funding/get_non_tradable_assets/{ccy}")
    public ResponseEntity<JSONObject> getNonTradableAssets(@PathVariable("ccy") String currency) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = fundingAPIService.getNonTradableAssets(currency);
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
