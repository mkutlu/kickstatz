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
    private boolean isBanned;

    @JsonProperty("playback_url")
    private String playbackUrl;

    @JsonProperty("name_updated_at")
    private String nameUpdatedAt;

    @JsonProperty("vod_enabled")
    private boolean vodEnabled;

    @JsonProperty("subscription_enabled")
    private boolean subscriptionEnabled;

    @JsonProperty("is_affiliate")
    private boolean isAffiliate;

    private int followersCount;

    @JsonProperty("subscriber_badges")
    private List<SubscriberBadge> subscriberBadges;

    @JsonProperty("banner_image")
    private BannerImage bannerImage;

    @JsonProperty("recent_categories")
    private List<Category> recentCategories;

    private Livestream livestream;
    private String role;
    private boolean muted;

    @JsonProperty("follower_badges")
    private List<String> followerBadges;

    @JsonProperty("offline_banner_image")
    private BadgeImage offlineBannerImage;

    @JsonProperty("can_host")
    private boolean canHost;

    private User user;
    private Chatroom chatroom;

    @JsonProperty("ascending_links")
    private List<Link> ascendingLinks;

    private Plan plan;

    @JsonProperty("previous_livestreams")
    private List<Livestream> previousLivestream;

    private List<Video> videos;

    private Verified verified;

    private List<Media> media;

}