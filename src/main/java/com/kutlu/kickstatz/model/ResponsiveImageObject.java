package com.kutlu.kickstatz.model;


import lombok.Data;

import java.util.List;

@Data
public class ResponsiveImageObject {
    private List<String> urls;
    private String base64svg;
}