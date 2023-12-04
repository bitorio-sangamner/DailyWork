package rsm.project.WebSocketExternalDemo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rsm.project.WebSocketExternalDemo.service.OkxConnectionService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WebSocketClient {
    private static final int PING_DELAY_SECONDS = 25;
    private WebSocket webSocket;
    private Boolean isConnectedToOkxServer;
    private final ScheduledExecutorService executorService;
    private Boolean expectingPong = false;
    private final String url;
    private final String natureOfURL;
    private final OkxConnectionService okxConnectionService;
    
    public WebSocketClient(String url, String natureOfURL, OkxConnectionService okxConnectionService){
        this.url = url;
        this.natureOfURL = natureOfURL;
        this.okxConnectionService = okxConnectionService;
        executorService = Executors.newScheduledThreadPool(1);
    }
    public void connectToOkxServer() {
        OkHttpClient webSocketClient = new OkHttpClient.Builder()
                                                .readTimeout(5, TimeUnit.SECONDS)
                                                .build();

        Request request = new Request.Builder()
                                .url(url)
                                .build();

        webSocket = webSocketClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
                isConnectedToOkxServer = false;
                log.warn("Websocket connection to OKX(" + natureOfURL + ") server closed.");
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                isConnectedToOkxServer = false;
                log.error("Websocket connection to OKX(" + natureOfURL + ") server has been closed due to an error.");
                log.error("The error is {}", t.getMessage());
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String bytes) {
                super.onMessage(webSocket, bytes);
                expectingPong = false;
                log.info("Received message from OKX(" + natureOfURL + ") : {}", bytes);
                if (!bytes.equalsIgnoreCase("pong")) {
                    try {
                        sendResponseDataFromOKXTOLocalWebSocketMessage(bytes);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);
                isConnectedToOkxServer = true;
                log.info("Websocket connection to OKX(" + natureOfURL + ") server opened.");
                startPingTask();
            }
        });
    }

    public void sendResponseDataFromOKXTOLocalWebSocketMessage(String bytes) throws JsonProcessingException {
        if (bytes.equals("pong")) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(bytes);
        if (jsonNode.has("event")) {
            if (jsonNode.get("event").asText().equals("subscribe")) {
                return;
            }
            if (jsonNode.get("event").asText().equals("error")) {
                log.error("Received error from OKX(" + natureOfURL + ") server : {}", jsonNode.asText());
                return;
            }
        }
        okxConnectionService.formatDataFromOKX(jsonNode);
    }

    public void startPingTask() {
        executorService.scheduleAtFixedRate(() -> {
            if (!expectingPong) {
                sendPing();
            } else {
                handlePongNotReceived();
                executorService.shutdown();
            }
        }, PING_DELAY_SECONDS, PING_DELAY_SECONDS, TimeUnit.SECONDS);
    }

    private void sendPing() {
        webSocket.send("ping");
        expectingPong = true;
    }

    private void handlePongNotReceived() {
        expectingPong = false;
        this.connectToOkxServer();
    }

    public void sendToServer(String str) {
        if (null != webSocket) {
            if (webSocket.send(str)) {
                log.info("\n{} sent to server successfully", str);
                return;
            }
            log.error("\n{} not sent to server due to connection error.", str);
        } else {
            log.warn("Please establish the connection before you sent to it！");
        }
    }
}
