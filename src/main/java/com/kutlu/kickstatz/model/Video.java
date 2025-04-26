package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * "video": {
 *                 "id": 52116721,
 *                 "live_stream_id": 55626680,
 *                 "slug": null,
 *                 "thumb": null,
 *                 "s3": null,
 *                 "trading_platform_id": null,
 *                 "created_at": "2025-04-26T15:05:44.000000Z",
 *                 "updated_at": "2025-04-26T20:12:32.000000Z",
 *                 "uuid": "7660866f-1c9d-4c57-a33d-1448c7d0dd82",
 *                 "views": 655,
 *                 "deleted_at": null,
 *                 "is_pruned": false,
 *                 "is_private": false,
 *                 "status": "public"
 *             }
 */
@Data
public class Video {
    private int id;

    @JsonProperty("live_stream_id")
    private int liveStreamId;

    private String slug;
    private String thumb;
    private String s3;

    @JsonProperty("trading_platform_id")
    private int tradingPlatformId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    private String uuid;
    private int views;

    @JsonProperty("deleted_at")
    private String deletedAt;

    @JsonProperty("is_pruned")
    private boolean isPruned;

    @JsonProperty("is_private")
    private boolean isPrivate;
    private String status;
}