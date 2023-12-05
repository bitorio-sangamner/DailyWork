package rsm.project.WebSocketExternalDemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface OkxConnectionService {
    void connect();

    void sendToServer(String subscriptionMessage);

    void pushDataToLocalWebsocket(String topic, JsonNode jsonNode);

    void formatDataFromOKX(JsonNode jsonNode) throws JsonProcessingException;
}
