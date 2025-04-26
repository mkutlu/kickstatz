package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubscriberBadge {

    private int id;

    @JsonProperty("channel_id")
    private int channelId;

    private int months;

    @JsonProperty("badge_image")
    private BadgeImage badgeImage;
}