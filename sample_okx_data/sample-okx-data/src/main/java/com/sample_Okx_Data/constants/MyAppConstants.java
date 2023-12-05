package com.sample_Okx_Data.constants;


import org.springframework.stereotype.Component;

@Component
public class MyAppConstants {

    public static final String btcUsdtPair = "BTC-USDT";
    public static final String ethUsdtPair = "ETH-USDT";
    public static final String maticUsdtPair = "MATIC-USDT";

    //************************************************************
     //currencies
    public static final String btcCurrency="BTC";
    public static final String ethCurrency="ETH";
    public static final String maticCurrency="MATIC";
    public static final String bnbCurrency="BNB";
    public static final String okbCurrency="OKB";

    //***********************************************************************
    //instrument Type
    public static final String option="OPTION";
    public static final String futures="FUTURES";
    public static final String swap="SWAP";
    public static final String margin="MARGIN";
    public static final String any="ANY";

    //***************************************************************************
      //spreadId
    public static final String btcUsdtSpread="BTC-USDT_BTC-USDT-SWAP";
    public static final String ethUsdtSpread="ETH-USDT_ETH-USDT-SWAP";
    public static final String maticUsdtSpread="MATIC-USDT_MATIC-USDT-SWAP";
    public static final String bnbUsdtSpread="BNB-USDT_BNB-USDT-SWAP";
    public static final String okbUsdtSpread="OKB-USDT_OKB-USDT-SWAP";

    //****************************************************************************

    private MyAppConstants() {
        try {
            throw new AssertionError("Instantiation not allowed for utility class");
        } catch (AssertionError e) {
            // Log the exception or handle it as needed
            System.err.println("Error in MyAppConstants constructor: " + e.getMessage());
        }
    }
}
