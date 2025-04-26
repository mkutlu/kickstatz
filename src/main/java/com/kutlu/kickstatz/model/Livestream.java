package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Livestream {
    private int id;
    private String slug;

    @JsonProperty("channel_id")
    private int channelId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("session_title")
    private String sessionTitle;

    @JsonProperty("is_live")
    private boolean isLive;

    @JsonProperty("risk_level_id")
    private String riskLevelId;

    @JsonProperty("start_time")
    private String startTime;

    private String source;

    @JsonProperty("twitch_channel")
    private String twitchChannel;

    private int duration;
    private String language;

    @JsonProperty("is_mature")
    private boolean isMature;

    @JsonProperty("viewer_count")
    private int viewerCount;

    private Thumbnail thumbnail;
    private int viewers;
    private int views;

    @JsonProperty("lang_iso")
    private String langIso;

    private List<Category> categories;
    private List<String> tags;
    private Video video;
}