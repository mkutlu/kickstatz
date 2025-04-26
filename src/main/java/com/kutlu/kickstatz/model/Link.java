package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * "ascending_links": [
 *         {
 *             "id": 2002579,
 *             "channel_id": 2340642,
 *             "description": "",
 *             "link": "https:\/\/rain.gg\/r\/ATRO",
 *             "created_at": "2025-04-22T19:29:27.000000Z",
 *             "updated_at": "2025-04-22T19:29:53.000000Z",
 *             "order": 0,
 *             "title": "w"
 *         },
 *   ]
 */
@Data
public class Link {
    private int id;
    @JsonProperty("channel_id")
    private int channelId;
    private String description;
    private String link;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private int order;
    private String title;
}
