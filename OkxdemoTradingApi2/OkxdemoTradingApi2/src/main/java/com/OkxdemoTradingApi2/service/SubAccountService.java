package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.subAccount.param.SetSubAccountLoanAllocation;
import com.okex.open.api.bean.subAccount.param.SetTransferOut;
import com.okex.open.api.bean.subAccount.param.SubAccountTransfer;
import com.okex.open.api.service.subAccount.SubAccountAPIService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class SubAccountService
{
    private final SubAccountAPIService subAccountAPIService;

    public SubAccountService(SubAccountAPIService subAccountAPIService) {
        this.subAccountAPIService = subAccountAPIService;
    }

    public JSONObject getSubAccountList()
    {
        JSONObject json=subAccountAPIService.getSubAccountList("","","","","");
        return json;
    }

    public JSONObject getSubAccountBalances(String subAccountName)
    {
        JSONObject json=subAccountAPIService.getSubAccountBalances(subAccountName);
        return json;
    }

    public JSONObject getSubAccountBills(String ccy)
    {
        JSONObject json=subAccountAPIService.getSubAccountBills(ccy,"","","","","");
        return json;
    }

    public JSONObject subAccountTransfer(SubAccountTransfer subAccountTransferObj)
    {
        JSONObject json=subAccountAPIService.subAccountTransfer(subAccountTransferObj);
        return json;
    }

    public JSONObject setTransferOut(SetTransferOut setTransferOut)
    {
        JSONObject json=subAccountAPIService.setTransferOut(setTransferOut);
        return json;
    }

    public JSONObject getEntrustSubList()
    {
        JSONObject json=subAccountAPIService.getEntrustSubList("");
        return json;
    }

    public JSONObject getSubaccountBalances(@PathVariable String subAccountName)
    {
        JSONObject json=subAccountAPIService.getSubaccountBalances(subAccountName,"");
        return json;
    }

    public JSONObject modifySubApikey(SetTransferOut setTransferOut)
    {
        JSONObject json=subAccountAPIService.modifySubApikey(setTransferOut);
        return json;
    }

    public JSONObject ifRebate(String apiKey)
    {
        JSONObject json=subAccountAPIService.ifRebate(apiKey);
        return json;
    }

    public JSONObject setSubAccountLoanAllocation(SetSubAccountLoanAllocation subAccountLoanAllocationObj)
    {
        JSONObject json=subAccountAPIService.setSubAccountLoanAllocation(subAccountLoanAllocationObj);
        return json;
    }

    public JSONObject getSubAccountInterestLimits(String subAcct, String ccy)
    {
        JSONObject json=subAccountAPIService.getSubAccountInterestLimits(subAcct,ccy);
        return json;
    }


}
