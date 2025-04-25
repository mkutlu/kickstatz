package com.kutlu.kickstatz.service.websocket;

import com.kutlu.kickstatz.model.ChatMessage;
import com.kutlu.kickstatz.model.ChatUser;

import com.kutlu.kickstatz.service.cache.ReactiveRedisService;
import com.kutlu.kickstatz.service.persistance.MessagePersistenceService;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KickWebSocketHandler extends TextWebSocketHandler {

    private final ReactiveRedisService redisService;
    private final MessagePersistenceService dbService;
    private final ObjectMapper objectMapper;

    public KickWebSocketHandler(ReactiveRedisService redisService, MessagePersistenceService dbService) {
        this.redisService = redisService;
        this.dbService = dbService;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Handles incoming WebSocket messages and processes them.
     *
     * @param session The WebSocket session.
     * @param message The incoming message.
     * @throws IOException If there is an error during message parsing.
     */
    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) throws IOException {
        // Parse the incoming message to a JSON object
        JsonNode jsonNode = objectMapper.readTree(message.getPayload());

        // Create a ChatMessage object and populate its fields with the data from the JSON
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(jsonNode.get("id").asText());
        chatMessage.setChannelId(jsonNode.get("channel_id").asText());
        chatMessage.setUserId(jsonNode.get("user").get("id").asText());
        chatMessage.setUsername(jsonNode.get("user").get("username").asText());
        chatMessage.setContent(jsonNode.get("content").asText());
        chatMessage.setTimestamp(java.time.Instant.parse(jsonNode.get("timestamp").asText()));

        // Create a ChatUser object for the user who sent the message
        ChatUser user = new ChatUser();
        user.setId(chatMessage.getUserId());
        user.setUsername(chatMessage.getUsername());
        user.setChannelId(chatMessage.getChannelId());

        // Save the chat message and user data to Redis (reactive approach)
        redisService.saveLastMessage(chatMessage).subscribe();
        redisService.saveUser(user).subscribe();

        // Save the chat message and user data to the database
        dbService.saveMessage(chatMessage);
        dbService.saveUser(user);
    }
}