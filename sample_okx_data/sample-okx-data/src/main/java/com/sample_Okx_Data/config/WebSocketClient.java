package com.sample_Okx_Data.config;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import okhttp3.*;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class WebSocketClient {

    private WebSocket webSocket;
    private  String signature;
    public void connection (String url){
        OkHttpClient client = new OkHttpClient();

        // Replace the URL with your WebSocket server URL
        Request request = new Request.Builder().url(url).build();

        WebSocketListener webSocketListener = new WebSocketListener() {


            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                System.out.println("WebSocket Connection Opened");

                /*
                    This code is using the ScheduledExecutorService
                     to schedule a task to be executed periodically at a fixed rate.
                 */

                /*
                   ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                  creates a new single-threaded executor
                  that can schedule commands to run after a given delay or to execute periodically
                 */
                try {
                    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                    executorService.scheduleAtFixedRate(() -> {
                        webSocket.send("ping");
                        System.out.println("Sent ping");
                    }, 10, 20, TimeUnit.SECONDS);
                }

                catch(Exception e)
                {
                   e.printStackTrace();
                }


            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {

                if(text.contains("\"channel\":\"books\"")) {

                    if(text.contains("\"action\":\"update\"")) {

                        // Convert the JSON string into a JSONObject using net.sf.json.JSONObject
                        JSONObject jsonObject= JSONObject.fromObject(text);

                        // Extract the "arg" and "data" fields from the JSON object
                        JSONObject arg=JSONObject.fromObject(jsonObject.get("arg"));
                        JSONArray data=JSONArray.fromObject(jsonObject.get("data"));

                        System.out.println("size of data array:"+data.size());
                       //separate ask and bid from JSONArray data
                        JSONObject dataStr=JSONObject.fromObject(data.get(0));

                        String dataOfInstrument=dataStr.toString();

                        //System.out.println("Received message: " + text);
                        System.out.println("Args:"+arg);
                        System.out.println("Data :"+dataOfInstrument);
                        System.out.println("*****************************************************************");
                    }

                   else if(text.contains("snapshot"))
                    {
                        // Convert the JSON string into a JSONObject using net.sf.json.JSONObject
                        JSONObject jsonObject=JSONObject.fromObject(text);

                        // Extract the "arg" and "data" fields from the JSON object
                        JSONObject args=JSONObject.fromObject(jsonObject.get("arg"));
                        JSONArray data=JSONArray.fromObject(jsonObject.get("data"));

                        System.out.println("size of data array:"+data.size());
                        //separate ask and bid from JSONArray data
                        JSONObject dataStr=JSONObject.fromObject(data.get(0));

                        String dataOfInstrument=dataStr.toString();

                        System.out.println("Args:"+args);
                        System.out.println("Data :"+dataOfInstrument);
                        System.out.println("*****************************************************************");
                    }
                }
                else if(text.contains("\"channel\":\"trades\""))
                {
                    System.out.println("Received message: "+text);
                    System.out.println("**********************************************************************");
                }
                else if(text.contains("\"channel\":\"tickers\""))
                {
                    System.out.println("Received message: "+text);
                    System.out.println("**********************************************************************");
                }

                else if(text.contains("\"channel\":\"account\""))
                {
                   System.out.println("Received message:"+text);
                    System.out.println("***********************************************************************");
                }

                else if(text.contains("\"channel\":\"account-greeks\""))
                {
                    System.out.println("Received message: " + text);
                    System.out.println("***********************************************************************");
                }

                else if(text.contains("pong"))
                {
                    System.out.println("Received message: " + text);
                    System.out.println("***********************************************************************");
                }

                else
                {
                    System.out.println("Received message: " + text);
                    System.out.println("************************************************************************");
                }


            }

//            @Override
//            public void onMessage(WebSocket webSocket, ByteString bytes) {
//
//                System.out.println("Received bytes: " + bytes.hex());
//            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                System.out.println("WebSocket Closing: " + code + " " + reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                System.out.println("WebSocket Closed: " + code + " " + reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                System.out.println("WebSocket Failure: " + t.getMessage());
            }
        };

         webSocket = client.newWebSocket(request, webSocketListener);

    }//connection method


    public void subscribePublicChannel(String instrument,String channelName)
    {
        String str = "{\"op\": \"subscribe\", \"args\":[{\"channel\":\""+channelName+"\",\"instId\":\"" + instrument + "\"}]}";
        sendMessage(str);
    }

    public void subscribeToPrivateChannel(String channelName,String sprdId,String currency,String instType)
    {
        if(sprdId.equals("")) {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\"" + channelName + "\"}]}";
            sendMessage(str);
        }
        else if(sprdId!=null)
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\",\"sprdId\":\""+sprdId+"\"}]}";
            sendMessage(str);
        }

        else if(channelName.equals("account") &&(currency!=null && !currency.equals("")))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\"" + channelName + "\",\"ccy\":\"" + currency + "\"}]}";
            sendMessage(str);
        }

        else if(channelName.equals("liquidation-warning")&&(instType!=null && !instType.equals("")))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\",\"instType\":\""+instType+"\"}]}";
            sendMessage(str);
        }

        else if(channelName.equals("account-greeks") && (currency!=null && !currency.equals("")))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\",\"ccy\":\""+currency+"\"}]}";
            sendMessage(str);
        }
    }
    public void subcribeToPrivatePositionChannel(String channel,String instType,String instFamily,String instId)
    {
        String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channel+"\",\"instType\":\""+instType+"\",\"instFamily\":\""+instFamily+"\",\"instId\":\""+instId+"\"}]}";
                         // "{\"op\":\"subscribe\",\"args\":[{\"channel\":\"positions\",\"instType\":\"FUTURES\",\"instFamily\":\"BTC-USD\",\"instId\":\"BTC-USD-200329\"}]}";
        sendMessage(str);
    }

    public void subscribePrivateBalanceAndPosition(String channelName)
    {
        String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\"" +channelName+ "\"}]}";
        sendMessage(str);
    }

    public void subscribePrivateDemoTradingChannel(String channelName,String currency,String instType,String instFamily,String instId,String sprdId)
    {
        if(channelName.equals("account") && currency!=null && (instType==null ||instType=="") &&(instFamily==null ||instFamily=="")&&(instId==null ||instId=="")) {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\"" + channelName + "\",\"ccy\":\"" + currency + "\"}]}";
            sendMessage(str);
        }

        else if((channelName.equals("positions")) &&(currency==null ||currency.equals("")))
        {
            if((instType.equals("FUTURES") ||instType.equals("OPTION") || instType.equals("SWAP") ||instType.equals("MARGIN")) && (instFamily!=null || !instFamily.equals("")) &&(instId!=null || !instId.equals("")))
            {
                String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\",\"instType\":\""+instType+"\",\"instFamily\":\""+instFamily+"\",\"instId\":\""+instId+"\"}]}";
                sendMessage(str);
            }
        }
        else if(channelName.equals("balance_and_position"))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\"}]}";
            sendMessage(str);
        }

        else if(channelName.equals("liquidation-warning") && (instType.equals("SWAP") ||instType.equals("OPTION") || instType.equals("FUTURES") || instType.equals("MARGIN") || instType.equals("ANY")))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\",\"instType\":\""+instType+"\"}]}";
            sendMessage(str);
        }

        else if(channelName.equals("account-greeks") &&(currency.equals("BTC") || currency.equals("ETH") || currency.equals("MATIC") || currency.equals("BNB") || currency.equals("OKB")))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\",\"ccy\":\""+currency+"\"}]}";
            sendMessage(str);
        }

        else if(channelName.equals("rfqs") && (currency==null || currency.equals("")) &&(instType==null || instType.equals("")) && (instFamily==null || instFamily.equals("")) && (instId==null || instId.equals("")))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\"}]}";
            sendMessage(str);
        }

        else if(channelName.equals("quotes") && (currency==null || currency.equals("")) &&(instType==null || instType.equals("")) && (instFamily==null || instFamily.equals("")) && (instId==null || instId.equals("")))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\"}]}";
            sendMessage(str);
        }

        else if(channelName.equals("struc-block-trades")&& (currency==null || currency.equals("")) &&(instType==null || instType.equals("")) && (instFamily==null || instFamily.equals("")) && (instId==null || instId.equals("")))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\"}]}";
            sendMessage(str);
        }

        else if(channelName.equals("sprd-orders") && (sprdId!=null || !sprdId.equals("") || sprdId!=null))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\",\"sprdId\":\""+sprdId+"\"}]}";
            sendMessage(str);
        }

        else if(channelName.equals("sprd-trades") && (sprdId!=null || !sprdId.equals("") || sprdId!=null))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\",\"sprdId\":\""+sprdId+"\"}]}";
            sendMessage(str);
        }
    }

    //***************************************************************************************************

    public void subscribedemoTradeApiChannel(String id,String operation,String sprdId,String clOrdId,String side,String orderType,String order_price,String size)
    {
        String str = "{\"id\":\""+id+"\",\"op\":\""+operation+"\",\"args\":[{\"sprdId\":\""+sprdId+"\",\"clOrdId\":\""+clOrdId+"\",\"side\":\""+side+"\",\"ordType\":\""+orderType+"\",\"px\":\""+order_price+"\",\"sz\":\""+size+"\"}]}";
        sendMessage(str);

    }

    public void subscribePrivateDemoTradChannel(String id,String operation,String side,String instId,String tdMode,String ordType,String size,String Channel,String instType,String instFamily,String orderId,String Order_Price,String newSz)
    {
        if(Channel.equals("orders"))
        {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+Channel+"\",\"instType\":\""+instType+"\",\"instFamily\":\""+instFamily+"\",\"instId\":\""+instId+"\"}]}";
           // String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+Channel+"\",\"instType\":\""+instType+"\"}]}";

            sendMessage(str);
        }
        else if(operation.equals("order") && (Order_Price==null ||Order_Price.equals(""))){
            String str = "{\"id\":\""+id+"\",\"op\":\""+operation+"\",\"args\":[{\"side\":\""+side+"\",\"instId\":\""+instId+"\",\"tdMode\":\""+tdMode+"\",\"ordType\":\""+ordType+"\",\"sz\":\""+size+"\"}]}";
            sendMessage(str);
        }

        else if(operation.equals("order") && (Order_Price!=null && !Order_Price.equals("")))
        {
            String str = "{\"id\":\""+id+"\",\"op\":\""+operation+"\",\"args\":[{\"side\":\""+side+"\",\"instId\":\""+instId+"\",\"tdMode\":\""+tdMode+"\",\"ordType\":\""+ordType+"\",\"sz\":\""+size+"\",\"px\":\""+Order_Price+"\"}]}";
            sendMessage(str);
        }

        else if(operation.equals("cancel-order"))
        {
            String str = "{\"id\":\""+id+"\",\"op\":\""+operation+"\",\"args\":[{\"instId\":\""+instId+"\",\"ordId\":\""+orderId+"\"}]}";
            sendMessage(str);
        }
        else if(operation.equals("amend-order"))
        {
            String str = "{\"id\":\""+id+"\",\"op\":\""+operation+"\",\"args\":[{\"instId\":\""+instId+"\",\"ordId\":\""+orderId+"\",\"newSz\":\""+newSz+"\"}]}";
            sendMessage(str);

        }




    }

    public void sendMessage(String str)
    {
        if(webSocket!=null)
        {
            System.out.println("Send a message to the server:::::" + str);
            boolean send = webSocket.send(str);

            System.out.println("Send a message to the server:::::" + send);
        }
        else
        {
            System.out.println("Please establish the connection before you operate it！");
        }

        }//sendMessage

    public void loginToOkx(String apiKey, String secretKey, String passphrase)
    {
        /* It gets the current timestamp in milliseconds, converts it to seconds, and stores it as a string.

         */
        String timestamp = DateTime.now().getMillis() / 1000 + "";

        /*
           Constructs a message using the timestamp, HTTP method ("GET"), and the request path ("/users/self/verify").
         */
        String message = timestamp + "GET" + "/users/self/verify";

        System.out.println("message :"+message);

        /*
           Calls the sha256_HMAC method to
           generate an HMAC-SHA256 signature using the constructed message and
           the provided secret key.
         */
        signature = sha256_HMAC(message, secretKey);

        System.out.println("signature:"+signature);

        String str = "{\"op\"" + ":" + "\"login\"" + "," + "\"args\"" + ":" + "[{" + "\"apiKey\"" + ":" + "\"" + apiKey + "\"" + "," + "\"passphrase\"" + ":" + "\"" + passphrase + "\"" + "," + "\"timestamp\"" + ":" + "\"" + timestamp + "\"" + "," + "\"sign\"" + ":" + "\"" + signature + "\"" + "}]}";
        sendMessage(str);
    }

    private  String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            /*
              Creates an instance of the HMAC-SHA256 algorithm.
               (Hash-based Message Authentication Code with SHA-256)
             */
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

            /*
               Creates a secret key specification using the provided secret and character encoding.
             */
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

            //Initializes the HMAC-SHA256 instance with the secret key.
            sha256_HMAC.init(secret_key);

            //Computes the HMAC-SHA256 hash for the given message.
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));

            //Encodes the hash as a Base64 string.
            hash = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }
}//class
