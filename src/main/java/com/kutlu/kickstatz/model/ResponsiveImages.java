package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kutlu.kickstatz.util.ResponsiveImagesDeserializer;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(using = ResponsiveImagesDeserializer.class)

public class ResponsiveImages {
    private List<ResponsiveImageObject> fullsize;
}
