package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChannelData {
    @JsonProperty("broadcaster_user_id")
    private int broadcasterUserId;

    private CategoryMeta category;

    @JsonProperty("channel_id")
    private int channelId;

    @JsonProperty("has_mature_content")
    private boolean hasMatureContent;

    private String language;
    private String slug;

    @JsonProperty("started_at")
    private String startedAt;

    @JsonProperty("stream_title")
    private String streamTitle;

    private String thumbnail;

    @JsonProperty("viewer_count")
    private int viewerCount;

}