package com.OkxdemoTradingApi2.controller;

import com.OkxdemoTradingApi2.service.SubAccountService;
import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.subAccount.param.SetSubAccountLoanAllocation;
import com.okex.open.api.bean.subAccount.param.SetTransferOut;
import com.okex.open.api.bean.subAccount.param.SubAccountTransfer;
import com.okex.open.api.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubAccountController {

    @Autowired
    private SubAccountService subAccountService;

    @GetMapping("/getSubAccountList")
    public ResponseEntity<Object> getSubAccountList()
    {
        JSONObject json=new JSONObject();

        try
        {
            json=subAccountService.getSubAccountList();
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/subAccountBalance/{subAccountName}")
    public ResponseEntity<Object> subAccountBalance(@PathVariable String subAccountName)
    {
        JSONObject json=new JSONObject();
       try
       {
           json=subAccountService.getSubAccountBalances(subAccountName);
       }

       catch(APIException e)
       {
           JSONObject jsonObject=new JSONObject();
           String msg=e.getMessage();

           jsonObject.put("message",msg);
           return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getSubAccountBills/{ccy}")
    public ResponseEntity<Object> getSubAccountBills(@PathVariable String ccy)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=subAccountService.getSubAccountBills(ccy);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/subAccountTransfer")
    public ResponseEntity<Object> subAccountTransfer(@RequestBody SubAccountTransfer subAccountTransferObj)
    {
        JSONObject json=new JSONObject();
       try
       {
           json=subAccountService.subAccountTransfer(subAccountTransferObj);
       }

       catch(APIException e)
       {
           JSONObject jsonObject=new JSONObject();
           String msg=e.getMessage();

           jsonObject.put("message",msg);
           return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/setTransferOut")
    public ResponseEntity<Object> setTransferOut(@RequestBody SetTransferOut setTransferOut)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=subAccountService.setTransferOut(setTransferOut);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getEntrustSubList")
    public ResponseEntity<Object> getEntrustSubList()
    {
        JSONObject json=new JSONObject();
        try
        {
            json=subAccountService.getEntrustSubList();
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getSubaccountBalances/{subAccountName}")
    public ResponseEntity<Object> getSubaccountBalances(@PathVariable String subAccountName)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=subAccountService.getSubAccountBalances(subAccountName);
        }
        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/modifySubApikey")
    public ResponseEntity<Object> modifySubApikey(@RequestBody SetTransferOut setTransferOutObj)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=subAccountService.modifySubApikey(setTransferOutObj);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/ifRebate/{apiKey}")
    public ResponseEntity<Object> ifRebate(@PathVariable String apiKey)
    {
        JSONObject json=new JSONObject();
       try
       {
           json=subAccountService.ifRebate(apiKey);
       }
       catch(APIException e)
       {
           JSONObject jsonObject=new JSONObject();
           String msg=e.getMessage();

           jsonObject.put("message",msg);
           return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/setSubAccountLoanAllocation")
    public ResponseEntity<Object> setSubAccountLoanAllocation(@RequestBody SetSubAccountLoanAllocation subAccountLoanAllocationObj)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=subAccountService.setSubAccountLoanAllocation(subAccountLoanAllocationObj);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getSubAccountInterestLimits/{subAcct}/{ccy}")
    public ResponseEntity<Object> getSubAccountInterestLimits(@PathVariable String subAcct,@PathVariable String ccy)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=subAccountService.getSubAccountInterestLimits(subAcct,ccy);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/getSubAccountMaxWithdrawal/{subAcc}/{ccy}")
    public ResponseEntity<Object> getSubAccountMaxWithdrawal(@PathVariable String subAcc,@PathVariable String ccy)
    {
        JSONObject json=new JSONObject();
        try
        {
            json=subAccountService.getSubAccountMaxWithdrawal(subAcc,ccy);
        }

        catch(APIException e)
        {
            JSONObject jsonObject=new JSONObject();
            String msg=e.getMessage();

            jsonObject.put("message",msg);
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
