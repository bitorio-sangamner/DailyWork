package com.orderWithDataBase.config;

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
public class WebsocketClient {

    //This variable is used to store the WebSocket connection.
     private WebSocket webSocket;
    private  String signature;

    public void connection (String url){
        OkHttpClient client = new OkHttpClient();

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

                System.out.println("Received message:"+text);
                System.out.println("*********************************************************");

            }

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

    public void subscribeAlgoOrderChannel(String channelName,String instrumentType,String instFamily,String instId)
    {
        if(channelName.equals("orders-algo")) {
            String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\""+channelName+"\",\"instType\":\""+instrumentType+"\",\"instFamily\":\""+instFamily+"\",\"instId\":\""+instId+"\"}]}";
            sendMessage(str);
        }
    }

    public void subscribeAlgoOrderChannel()
    {
        String str = "{\"op\":\"subscribe\",\"args\":[{\"channel\":\"orders-algo\",\"instType\":\"FUTURES\",\"instFamily\":\"null\",\"instId\":\"null\"}]}";
        sendMessage(str);
    }

    public void subscribeOrderChannel(String channelName,String instrumentType,String instFamily,String instId)
    {
        String str = "{ \"op\": \"subscribe\", \"args\": [ { \"channel\": \""+channelName+"\", \"instType\": \""+instrumentType+"\", \"instFamily\": \""+instFamily+"\", \"instId\": \""+instId+"\" } ] }";
        sendMessage(str);
    }
    public void subscribeOrderChannel()
    {
        String str = "{ \"op\": \"subscribe\", \"args\": [ { \"channel\": \"orders\", \"instType\": \"FUTURES\", \"instFamily\": \"null\", \"instId\": \"null\" } ] }";
        sendMessage(str);
    }

}
