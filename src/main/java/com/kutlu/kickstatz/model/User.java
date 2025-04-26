package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * "user": {
 *         "id": 2395233,
 *         "username": "Atro",
 *         "agreed_to_terms": true,
 *         "email_verified_at": "2023-03-29T07:57:13.000000Z",
 *         "bio": "",
 *         "country": "",
 *         "state": "",
 *         "city": "",
 *         "instagram": "atro.off",
 *         "twitter": "",
 *         "youtube": "c\/ATRO55",
 *         "discord": "https:\/\/discord.gg\/gy9ZAQqRha",
 *         "tiktok": "atro.of",
 *         "facebook": "Atro55",
 *         "profile_pic": "https:\/\/files.kick.com\/images\/user\/2395233\/profile_image\/conversion\/3a2ed43b-c57f-4bdc-b7cc-c410d4369d70-fullsize.webp"
 *     },
 */
@Data
public class User {
    private int id;
    private String username;
    @JsonProperty("agreed_to_terms")
    private boolean agreedToTerms;
    @JsonProperty("email_verified_at")
    private String emailVerifiedAt;
    private String bio;
    private String country;
    private String state;
    private String city;
    private String instagram;
    private String twitter;
    private String youtube;
    private String discord;
    private String tiktok;
    private String facebook;
    @JsonProperty("profile_pic")
    private String profilePic;
}
