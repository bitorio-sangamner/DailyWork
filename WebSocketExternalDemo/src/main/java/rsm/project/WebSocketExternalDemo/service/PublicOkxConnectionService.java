package rsm.project.WebSocketExternalDemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import rsm.project.WebSocketExternalDemo.WebSocketClient;

import java.util.List;

@Controller
@Slf4j
public class PublicOkxConnectionService implements OkxConnectionService {

    private WebSocketClient webSocketClient = null;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Override
    @MessageMapping("/connectToPublicOkx")
    public void connect() {
        webSocketClient = new WebSocketClient("wss://ws.okx.com:8443/ws/v5/public", "public", this);
        webSocketClient.connectToOkxServer();
        subscribeToBooksChannel(List.of("BTC-USDT"));
    }

    @Override
    public void send(String subscriptionMessage) {
        webSocketClient.sendToServer(subscriptionMessage);
    }

    public void subscribeToBooksChannel(List<String> swapCurrencies) {
        if (swapCurrencies.isEmpty()) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        String message = """
                {
                   "op": "subscribe",
                   "args": [
                      new
                   ]
                }
                """;
        String args = """
                      {
                         "channel": "books",
                         "instId": "swap"
                      }
                """;
        for (String i : swapCurrencies) {
            args = """
                      {
                         "channel": "books",
                         "instId": "swap"
                      }
                """;
            args = args.replace("swap", i);
        }
        message = message.replace("new", args);
        send(message);
    }

    @Override
    public void pushDataToLocalWebsocket(String topic, JsonNode jsonNode) {

    }

    @Override
    public void formatDataFromOKX(JsonNode jsonNode) throws JsonProcessingException {
        log.info("public information : {}", jsonNode);
    }
}
