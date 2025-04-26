package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * "chatroom": {
 *         "id": 2333131,
 *         "chatable_type": "App\\Models\\Channel",
 *         "channel_id": 2340642,
 *         "created_at": "2023-03-29T07:57:13.000000Z",
 *         "updated_at": "2025-04-26T15:07:34.000000Z",
 *         "chat_mode_old": "public",
 *         "chat_mode": "public",
 *         "slow_mode": true,
 *         "chatable_id": 2340642,
 *         "followers_mode": true,
 *         "subscribers_mode": false,
 *         "emotes_mode": false,
 *         "message_interval": 30,
 *         "following_min_duration": 0
 *     },
 */
@Data
public class Chatroom {
    private int id;
    @JsonProperty("chatable_type")
    private String chatableType;
    @JsonProperty("channel_id")
    private int channelId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("chat_mode_old")
    private String chatModeOld;
    @JsonProperty("chat_mode")
    private String chatMode;
    @JsonProperty("slow_mode")
    private boolean slowMode;
    @JsonProperty("chatable_id")
    private int chatableId;
    @JsonProperty("followers_mode")
    private boolean followersMode;
    @JsonProperty("subscribers_mode")
    private boolean subscribersMode;
    @JsonProperty("emotes_mode")
    private boolean emotesMode;
    @JsonProperty("message_interval")
    private int messageInterval;
    @JsonProperty("following_min_duration")
    private int followingMinDuration;
}
