package rsm.project.WebSocketExternalDemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import rsm.project.WebSocketExternalDemo.WebSocketClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

@Controller
@Slf4j
public class AccountOKXConnectionService implements OkxConnectionService{

    private final String APIKEY = "414ebd7d-73e0-4a9e-8aee-511c35eaca35";
    private final String SECRETKEY = "03BD391BA3B81DEACB7E986ADB011E25";
    private final String PASSPHRASE = "Test@1234";

    private WebSocketClient webSocketClient = null;
    @Override
    @MessageMapping("/connectToAccountOkx")
    public void connect() {
        webSocketClient = new WebSocketClient("wss://wspap.okx.com:8443/ws/v5/private?brokerId=9999", "account", this);
//        webSocketClient.connectToOkxServer();
        CompletableFuture<String> completableFuture = CompletableFuture.runAsync(() -> {
            webSocketClient.connectToOkxServer();
        }).thenApply(result -> {
            log.info("inside thenApply");
            loginToOKX(null);
            return "null";
        });
        if (completableFuture.isDone()) {
            subsribeToOrderChannel();
        }
//                .thenRunAsync(this::subsribeToOrderChannel);
    }

    @Override
    public void sendToServer(String message) {
        webSocketClient.sendToServer(message);
    }

    @Override
    public void pushDataToLocalWebsocket(String topic, JsonNode jsonNode) {

    }

    @Override
    public void formatDataFromOKX(JsonNode jsonNode) throws JsonProcessingException {
        String subscriptionMessage = """
                {
                  "op": "subscribe",
                  "args": [
                    {
                      "channel": "orders",
                      "instType": "SPOT"
                    }
                  ]
                }
                """;
        sendToServer(subscriptionMessage);
    }

    public void loginToOKX(String subscriptionMessage) {
        long time = System.currentTimeMillis() / 1000L;
        String loginMessage = """
                {
                   "op": "login",
                   "args": [
                      {
                         "apiKey": "APIKEY",
                         "passphrase": "PASSPHRASE",
                         "timestamp": "TIME",
                         "sign": "SIGN"
                      }
                   ]
                }
                """;

        final String SIGN;
        try {
            SIGN = calculateHMAC(time + "GET" + "/users/self/verify");
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        loginMessage = loginMessage.replace("APIKEY", APIKEY)
                .replace("PASSPHRASE", PASSPHRASE)
                .replace("TIME",String.valueOf(time))
                .replace("SIGN", SIGN);
        sendToServer(loginMessage);

//        String balanceMessage = """
//                {
//                    "op": "subscribe",
//                    "args": [{
//                        "channel": "balance_and_position"
//                    }]
//                }
//                """;
//        sendToServer(balanceMessage);
    }

    private String calculateHMAC(String data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(SECRETKEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256Hmac.init(secretKey);

        byte[] hmacBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hmacBytes);
    }

    private void subsribeToOrderChannel() {

    }
}
