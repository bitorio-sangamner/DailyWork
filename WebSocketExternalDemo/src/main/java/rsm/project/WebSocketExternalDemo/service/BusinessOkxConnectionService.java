package rsm.project.WebSocketExternalDemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import rsm.project.WebSocketExternalDemo.WebSocketClient;


@Controller
@Slf4j
public class BusinessOkxConnectionService implements OkxConnectionService {

    private WebSocketClient webSocketClient = null;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    @MessageMapping("/connectToOkx")
    public void connect() {
        webSocketClient = new WebSocketClient("wss://ws.okx.com:8443/ws/v5/business","business", this);
        webSocketClient.connectToOkxServer();
        sendToServer("Hello");
    }

    @Override
    public void sendToServer(String subscriptionMessage) {
        subscriptionMessage = """
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
                      }
                   ]
                }
                """;
        webSocketClient.sendToServer(subscriptionMessage);
    }

    @Override
    public void pushDataToLocalWebsocket(String topic, JsonNode jsonNode) {
        simpMessagingTemplate.convertAndSend(topic, jsonNode);
    }

    @Override
    public void formatDataFromOKX(JsonNode jsonNode) throws JsonProcessingException {
        if (jsonNode.get("arg").has("channel")) {
            String channel = jsonNode.get("arg").get("channel").asText();

            switch (channel) {
                case "sprd-books5" -> {
                    jsonNode = jsonNode.get("data");
                    for (JsonNode i : jsonNode) {
                        pushDataToLocalWebsocket("/topic/spreadTrading", i);
                    }
                }
                case "sprd-tickers" -> {
                    jsonNode = jsonNode.get("data");
                    for (JsonNode i : jsonNode) {
                        pushDataToLocalWebsocket("/topic/tickers", i);
                    }
                }
                case "sprd-public-trades" -> {
                    jsonNode = jsonNode.get("data");
                    for (JsonNode i : jsonNode) {
                        pushDataToLocalWebsocket("/topic/sprd-public-trades", i);
                    }
                }
                default -> {
                    log.warn("Unknown received message {}", jsonNode);
                }
            }
        }
    }
}
