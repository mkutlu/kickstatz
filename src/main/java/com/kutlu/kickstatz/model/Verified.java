package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * "verified": {
 *         "id": 7376,
 *         "channel_id": 2340642,
 *         "created_at": "2024-10-02T03:24:26.000000Z",
 *         "updated_at": "2024-10-02T03:24:26.000000Z"
 *     }
 */
@Data
public class Verified {
    private int id;
    @JsonProperty("channel_id")
    private int channelId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
}
