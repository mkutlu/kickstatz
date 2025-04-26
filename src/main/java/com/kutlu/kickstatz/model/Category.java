package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Category {
    private int id;
    private int category_id;
    private String name;
    private String slug;
    private List<String> tags;
    private String description;

    @JsonProperty("deleted_at")
    private String deletedAt;

    private int viewers;

    @JsonProperty("is_mature")
    private boolean isMature;

    @JsonProperty("is_promoted")
    private boolean isPromoted;

    private BannerImage banner;
    private CategoryMeta category;
}