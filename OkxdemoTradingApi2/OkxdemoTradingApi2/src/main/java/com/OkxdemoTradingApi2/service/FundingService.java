package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.funding.param.ConvertDustAssets;
import com.okex.open.api.bean.funding.param.FundsTransfer;
import com.okex.open.api.bean.funding.param.PiggyBankPurchaseRedemption;
import com.okex.open.api.bean.funding.param.Withdrawal;
import com.okex.open.api.service.funding.FundingAPIService;
import org.springframework.stereotype.Service;

@Service
public class FundingService
{
    private final FundingAPIService fundingAPIService;

    public FundingService(FundingAPIService fundingAPIService) {
        this.fundingAPIService = fundingAPIService;
    }

    public JSONObject getCurrencies()
    {
        JSONObject json=fundingAPIService.getCurrencies();
        return json;
    }

    public JSONObject getBalanceFromFundingAccount(String currencyName)
    {
        JSONObject json=fundingAPIService.getBalance(currencyName);
        return json;
    }

    public JSONObject fundsTransfer(FundsTransfer fundsTransferObj)
    {
        JSONObject json=fundingAPIService.fundsTransfer(fundsTransferObj);
        return json;
    }

    public JSONObject assetBillsDetails()
    {
        JSONObject json=fundingAPIService.assetBillsDetails("","","","","","");
        return json;
    }

    public JSONObject getDepositAddress(String currencyName)
    {
        JSONObject json=fundingAPIService.getDepositAddress(currencyName);
        return json;
    }

    public JSONObject getDepositHistory()
    {
        JSONObject json=fundingAPIService.getDepositHistory("","","","","","","","","");
        return json;
    }

    public JSONObject Withdrawal(Withdrawal withdrawalObj)
    {
        JSONObject json=fundingAPIService.Withdrawal(withdrawalObj);
        return json;
    }

    public JSONObject getWithdrawalHistory()
    {
        JSONObject json=fundingAPIService.getWithdrawalHistory("","","","","","","","","");
        return json;
    }

    public JSONObject getDepositWithdrawalStatus()
    {
        JSONObject json=fundingAPIService.getDepositWithdrawalStatus("","","","","");
        return json;
    }

    public JSONObject piggyBankPurchaseRedemption(PiggyBankPurchaseRedemption piggyBankPurchaseRedemptionObj)
    {
        JSONObject json=fundingAPIService.piggyBankPurchaseRedemption(piggyBankPurchaseRedemptionObj);
        return json;
    }

    public JSONObject piggyBalance(String ccy)
    {
        JSONObject json=fundingAPIService.piggyBalance(ccy);
        return json;
    }

    public JSONObject depositLightning(String ccy, String amt, String to)
    {
        JSONObject json=fundingAPIService.depositLightning(ccy,amt,to);
        return json;
    }

    public JSONObject withdrawalLightning(Withdrawal withdrawalObj)
    {
        JSONObject json=fundingAPIService.withdrawalLightning(withdrawalObj);
        return json;
    }

    public JSONObject transferState(String transId,String clientId, String type)
    {
        JSONObject json=fundingAPIService.transferState(transId,clientId,type);
        return json;
    }

    public JSONObject assetValuation()
    {
        JSONObject json=fundingAPIService.assetValuation("");
        return json;
    }

    public JSONObject getNonTradableAssets()
    {
        JSONObject json=fundingAPIService.getNonTradableAssets("");
        return json;
    }

    public JSONObject cancelWithdrawal(Withdrawal cancelWithdrawal)
    {
        JSONObject json=fundingAPIService.cancelWithdrawal(cancelWithdrawal);
        return json;
    }

    public JSONObject convertDustAssets(ConvertDustAssets convertDustAssetsObj)
    {
        JSONObject json=fundingAPIService.convertDustAssets(convertDustAssetsObj);
        return json;
    }


}
