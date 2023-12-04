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

    private MyAppConstants() {
        try {
            throw new AssertionError("Instantiation not allowed for utility class");
        } catch (AssertionError e) {
            // Log the exception or handle it as needed
            System.err.println("Error in MyAppConstants constructor: " + e.getMessage());
        }
    }
}
