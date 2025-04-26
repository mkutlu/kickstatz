package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomProperty {
    @JsonProperty("generated_conversions")
    private Conversion generatedConversion;
}
