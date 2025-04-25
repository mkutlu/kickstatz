package com.kutlu.kickstatz.service.websocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WebSocketClientService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketClientService.class);

    @Value("${kick.websocket.url}")
    private String websocketBaseUrl;

    KickWebSocketHandler kickWebSocketHandler;

    /**
     * Constructor for WebSocketClientService.
     *
     * @param kickWebSocketHandler The WebSocket handler to manage incoming messages.
     */
    public WebSocketClientService(KickWebSocketHandler kickWebSocketHandler) {
        this.kickWebSocketHandler = kickWebSocketHandler;
    }

    /**
     * Connects to the WebSocket server using the specified chatroom and channel IDs.
     *
     * @param chatroomId The ID of the chatroom.
     * @param channelId The ID of the channel.
     */
    public void connect(String chatroomId, String channelId) {
        // Construct the WebSocket URL using the base URL and channel/chatroom ID
        String url = String.format(websocketBaseUrl, chatroomId, channelId);

        // Create the WebSocket client
        WebSocketClient client = new StandardWebSocketClient();

        // Initialize the WebSocket connection manager with the client and handler
        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(client, kickWebSocketHandler, url);

        // Start the WebSocket connection
        connectionManager.start();

        // Log successful connection
        logger.info("Successfully connected to WebSocket at URL: {}", url);
    }
}
