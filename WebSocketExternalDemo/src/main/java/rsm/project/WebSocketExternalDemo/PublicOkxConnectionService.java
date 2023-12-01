package rsm.project.WebSocketExternalDemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class PublicOkxConnectionService implements OkxConnectionService{

    private WebSocketClient webSocketClient = null;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Override
    @MessageMapping("/connectToPublicOkx")
    public void connect() {
        webSocketClient = new WebSocketClient("wss://ws.okx.com:8443/ws/v5/public", "public", this);
        webSocketClient.connectToOkxServer();
        send();
    }

    @Override
    public void send() {
        String subscriptionMessage = """
                {
                   "op": "subscribe",
                   "args": [
                      {
                         "channel": "books",
                         "instId": "BTC-USDT"
                      }
                   ]
                }
                """;
        webSocketClient.sendToServer(subscriptionMessage);
    }

    @Override
    public void pushDataToLocalWebsocket(String topic, JsonNode jsonNode) {

    }

    @Override
    public void formatDataFromOKX(JsonNode jsonNode) throws JsonProcessingException {
        log.info("public information : {}", jsonNode);
    }
}
