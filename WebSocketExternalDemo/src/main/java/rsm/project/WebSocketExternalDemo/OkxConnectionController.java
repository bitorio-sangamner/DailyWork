package rsm.project.WebSocketExternalDemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.http.WebSocket;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Controller
@Slf4j
public class OkxConnectionController {

    private WebSocketClient client = null;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/connectToOkx")
    public void okxConnect() {
        client = new WebSocketClient("wss://ws.okx.com:8443/ws/v5/business", this);
        client.connectToOkxServer();
        okxSendMessages();
    }

    public void okxSendMessages() {
        String subscriptionMessage = """
           {
              "op": "subscribe",
              "args": [
                 {
                    "channel": "sprd-tickers",
                    "sprdId": "BTC-USDT_BTC-USDT-SWAP"
                 },
                 {
                    "channel": "sprd-books5",
                    "sprdId": "BTC-USDT_BTC-USDT-SWAP"
                 },
                 {
                    "channel": "sprd-public-trades",
                    "sprdId": "BTC-USDT_BTC-USDT-SWAP"
                 },
                 {
                    "channel": "public-struc-block-trades"
                 },
                 {
                    "channel": "public-block-trades",
                    "instId": "BTC-USDT-SWAP"
                 },
                 {
                    "channel": "block-tickers",
                    "instId": "BTC-USDT"
                 },
                 {
                    "channel": "books",
                    "instId": "BTC-USDT"
                 }
              ]
           }
           """;
        client.sendMessageToOkxServer(subscriptionMessage);
    }

    public void senMessageToLocalWebsocket(String topic, JsonNode jsonNode) {
        simpMessagingTemplate.convertAndSend(topic, jsonNode);
    }
}
