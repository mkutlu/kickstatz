package com.kutlu.kickstatz.model;

import lombok.Data;

import java.util.List;

@Data
public class ChannelResponse {
    private List<ChannelData> data;
}
