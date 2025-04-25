package com.kutlu.kickstatz.model;

import lombok.Data;

import java.time.Instant;

@Data
public class ChatMessage {
    private String id;
    private String channelId;
    private String userId;
    private String username;
    private String content;
    private Instant timestamp;
}