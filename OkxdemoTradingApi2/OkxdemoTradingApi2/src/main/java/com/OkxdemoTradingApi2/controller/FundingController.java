package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.FundingService;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.funding.param.ConvertDustAssets;
import com.okex.open.api.bean.funding.param.FundsTransfer;
import com.okex.open.api.bean.funding.param.PiggyBankPurchaseRedemption;
import com.okex.open.api.bean.funding.param.Withdrawal;
import com.okex.open.api.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class FundingController
{
    private static final Logger logger = LoggerFactory.getLogger(FundingController.class.getName());

    @Autowired
   private FundingService fundingService;

   @GetMapping("/getCurrenciesFromFunding")
   public ResponseEntity<Object> getCurrenciesFromFundingAccount()
   {
       JSONObject json=new JSONObject();
      try
      {
          logger.debug("inside getCurrenciesFromFundingAccount !!");
          json= fundingService.getCurrencies();
      }

      catch(APIException e)
      {
        JSONObject jsonObj=new JSONObject();
        String msg=e.getMessage();

          jsonObj.put("message",msg);
          return ResponseEntity.of(Optional.of(jsonObj));
      }

      return ResponseEntity.of(Optional.of(json));

   }//getCurrenciesFromFundingAccount

    @GetMapping("/getBalance/{currencyName}")
    public ResponseEntity<Object> getBalanceFromFundingAccount(@PathVariable String currencyName)
    {
        JSONObject json=new JSONObject();
       try
       {
           logger.debug("inside getBalanceFromFundingAccount !!");
           json=fundingService.getBalanceFromFundingAccount(currencyName);
       }//try

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();

            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(json, HttpStatus.OK);

    }//getBalanceFromFundingAccount

    @PostMapping("/fundsTransfer")
    public ResponseEntity<Object> fundsTransfer(@Valid @RequestBody FundsTransfer fundsTransferObj, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        JSONObject json=new JSONObject();
        try
        {
            logger.debug("inside fundsTransfer !!");
            json=fundingService.fundsTransfer(fundsTransferObj);
        }//try

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity(jsonObject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }//fundsTransfer

    @GetMapping("/assetBillsDetails")
    public ResponseEntity<Object> assetBillsDetails()
    {
        JSONObject json=new JSONObject();
      try
      {
          logger.debug("inside assetBillsDetails !!");
          json=fundingService.assetBillsDetails();
      }//try

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity(jsonObject,HttpStatus.BAD_REQUEST);
        }

      return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/getDepositeAddress/{currency}")
    public ResponseEntity<Object> getDepositAddress(@PathVariable String currency)
    {
        JSONObject json=new JSONObject();
        try {
            logger.debug("inside getDepositAddress !!");
            json=fundingService.getDepositAddress(currency);

        }//

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity(jsonObject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/getDepositHistory")
    public ResponseEntity<Object> getDepositHistory()
    {
        JSONObject json=new JSONObject();

        try
        {
            logger.debug("inside getDepositHistory !!");
           json=fundingService.getDepositHistory();
        }//try

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();
            jsonObject.put("message",msg);

            return new ResponseEntity(jsonObject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }

    @PostMapping("/Withdrawal")
    public ResponseEntity<Object> Withdrawal(@Valid @RequestBody Withdrawal withdrawalObj,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JSONObject json=new JSONObject();
        try
        {
            logger.debug("inside Withdrawal !!");
            json=fundingService.Withdrawal(withdrawalObj);
        }//try

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/getWithdrawalHistory")
    public ResponseEntity<Object> getWithdrawalHistory()
    {
        JSONObject json=new JSONObject();
        try
        {
            logger.debug("inside getWithdrawalHistory !!");
            json=fundingService.getWithdrawalHistory();
        }//try

        catch(APIException e)
        {
           JSONObject jsonObject=new JSONObject();
           String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity(jsonObject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/getDepositWithdrawalStatus")
     public ResponseEntity<Object> getDepositWithdrawalStatus()
    {
        JSONObject json=new JSONObject();
        try {
            json = fundingService.getDepositWithdrawalStatus();

        }//try
        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity(jsonObject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @PostMapping("/piggyBankPurchaseRedemption")
    public ResponseEntity<Object> piggyBankPurchaseRedemption(@Valid @RequestBody PiggyBankPurchaseRedemption piggyBankPurchaseRedemption,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JSONObject json=new JSONObject();
       try
       {
           json=fundingService.piggyBankPurchaseRedemption(piggyBankPurchaseRedemption);
       }//try

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity(jsonObject,HttpStatus.BAD_REQUEST);
        }

       return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/piggyBalance/{currencyName}")
    public ResponseEntity<Object> piggyBalance(@PathVariable String currencyName)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=fundingService.piggyBalance(currencyName);
        }//

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity(jsonObject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @GetMapping("/depositLightning")
    public ResponseEntity<Object> depositLightning(@RequestBody JSONObject jsonObject)
    {
        JSONObject json=new JSONObject();
        try
        {
           String ccy=jsonObject.getString("ccy");
           String amt=jsonObject.getString("amt");
           String to=jsonObject.getString("to");

            json=fundingService.depositLightning(ccy,amt,to);

        }//try

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json,HttpStatus.OK);

    }

    @PostMapping("/withdrawalLightning")
    public ResponseEntity<Object> withdrawalLightning(@Valid @RequestBody Withdrawal withdrawalObj,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JSONObject json=new JSONObject();
        try
        {
            json=fundingService.withdrawalLightning(withdrawalObj);
        }

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/transferState")
    public ResponseEntity<Object> transferState(@RequestBody JSONObject jobj)
    {
        String transId=jobj.getString("transId");
        String clientId=jobj.getString("clientId");
        String type=jobj.getString("type");

        JSONObject json=new JSONObject();
       try
       {
           json=fundingService.transferState(transId,clientId,type);
       }//try

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/assetValuation")
    public ResponseEntity<Object> assetValuation()
    {
        JSONObject json=new JSONObject();
        try
        {
            json=fundingService.assetValuation();
        }

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }

    @GetMapping("/getNonTradableAssets")
    public ResponseEntity<Object> getNonTradableAssets()
    {
        JSONObject json=new JSONObject();
        try
        {
            json=fundingService.getNonTradableAssets();
        }//try

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(json,HttpStatus.OK);
    }

    @PostMapping("/cancelWithdrawal")
    public ResponseEntity<Object> cancelWithdrawal(@Valid @RequestBody Withdrawal cancelWithdrawal,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JSONObject json=new JSONObject();

        try
        {
            json=fundingService.cancelWithdrawal(cancelWithdrawal);
        }
        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json,HttpStatus.OK);
    }

    @PostMapping("/convertDustAssets")
    public ResponseEntity<Object> convertDustAssets(@Valid @RequestBody ConvertDustAssets convertDustAssetsObj,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JSONObject json=new JSONObject();

        try
        {
            json=fundingService.convertDustAssets(convertDustAssetsObj);
        }

        catch(APIException e)
        {
            JSONObject jsonobject=new JSONObject();
            String msg=e.getMessage();

            jsonobject.put("message",msg);
            return new ResponseEntity(jsonobject,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(json,HttpStatus.OK);
    }
}
