package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class ChannelDetailResponse {
    private int id;

    @JsonProperty("user_id")
    private int userId;

    private String slug;

    @JsonProperty("is_banned")
    private boolean banned;

    @JsonProperty("playback_url")
    private String playbackUrl;

    private String nameUpdatedAt;

    @JsonProperty("vod_enabled")
    private boolean vodEnabled;

    @JsonProperty("subscription_enabled")
    private boolean subscriptionEnabled;

    @JsonProperty("is_affiliate")
    private boolean affiliate;

    private int followersCount;

    private List<SubscriberBadge> subscriberBadges;

    private BannerImage bannerImage;
}