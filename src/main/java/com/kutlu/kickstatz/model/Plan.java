package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *  "plan": {
 *         "id": 80480,
 *         "channel_id": 2340642,
 *         "stripe_plan_id": "plan_RCr3QzWyiSttrQ",
 *         "amount": "4.99",
 *         "created_at": "2024-11-12T21:09:49.000000Z",
 *         "updated_at": "2024-11-12T21:09:49.000000Z"
 *     },
 */
@Data
public class Plan {
    private int id;
    @JsonProperty("channel_id")
    private int channelId;
    @JsonProperty("stripe_plan_id")
    private String stripePlanId;
    private String amount;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
}
