package com.OkxdemoTradingApi2.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.service.account.AccountAPIService;
import com.okex.open.api.service.publicData.PublicDataAPIService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import static com.okex.open.api.client.ApiHttp.JSON;


@Service
public class DemoAPIService<JSON> {

    private final APIConfiguration apiConfiguration;
    private final CloseableHttpClient httpClient;

    //private final PublicDataAPIServiceImpl publicDataAPIServiceImpl;
    @Autowired
    private final PublicDataAPIService publicDataAPIService;

    @Autowired
    private final AccountAPIService accountAPIService;

    public DemoAPIService(APIConfiguration apiConfiguration, CloseableHttpClient httpClient, PublicDataAPIService publicDataAPIService, AccountAPIService accountAPIService) {
        this.apiConfiguration = apiConfiguration;
        this.httpClient = httpClient;
        this.publicDataAPIService = publicDataAPIService;
        this.accountAPIService = accountAPIService;
    }

    /*public DemoAPIService(APIConfiguration apiConfiguration, CloseableHttpClient httpClient,  PublicDataAPIService publicDataAPIService) {
        this.apiConfiguration = apiConfiguration;
        this.httpClient = httpClient;

        //this.publicDataAPIServiceImpl = publicDataAPIServiceImpl;
        //this.publicDataAPIService = publicDataAPIService;
        this.publicDataAPIService = publicDataAPIService;
    }*/

  /*  public String callDemoAPI() throws Exception {
        String url = apiConfiguration.getDomain();
        System.out.println(url);
        HttpGet request = new HttpGet(url);

        HttpResponse response = httpClient.execute(request);
        System.out.println(response);
        // Process the response and return the result
        System.out.println(response.getStatusLine());
        System.out.println(response.getAllHeaders());
        return String.valueOf((response.getStatusLine()));
        //return response.toString();
    }*/

   public JSONObject callDemoAPI()
   {
       //PublicDataAPIService publicDataAPIService = new PublicDataAPIServiceImpl(apiConfiguration);
       publicDataAPIService.getSystemTime();
       System.out.println(publicDataAPIService.getSystemTime());
       return publicDataAPIService.getSystemTime();

      /* PublicDataAPIService publicDataAPIService = new PublicDataAPIServiceImpl(apiConfiguration);

       JSONObject systemTime = publicDataAPIService.getSystemTime();
       System.out.println("System Time: " + systemTime);
       return systemTime;*/


   }

   public JSONObject getInstrument()
   {
       publicDataAPIService.getInstruments("SWAP",null,null,null);
       System.out.println(publicDataAPIService.getInstruments("SWAP",null,null,null));
       return publicDataAPIService.getInstruments("SWAP",null,null,null);

   }

   public JSONObject getAccountDetails()
   {
       accountAPIService.getAccountConfiguration();
       return accountAPIService.getAccountConfiguration();
   }

   public JSONObject getAmount(String currency)
   {
       accountAPIService.getBalance(currency);
       System.out.println(accountAPIService.getBalance(currency));
       return accountAPIService.getBalance(currency);
   }
}
