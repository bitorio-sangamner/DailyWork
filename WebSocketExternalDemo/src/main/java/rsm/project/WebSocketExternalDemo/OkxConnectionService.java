package rsm.project.WebSocketExternalDemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface OkxConnectionService {
    public void connect();

    public void send();

    public void pushDataToLocalWebsocket(String topic, JsonNode jsonNode);

    public void formatDataFromOKX(JsonNode jsonNode) throws JsonProcessingException;
}
