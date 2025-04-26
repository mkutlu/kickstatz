package com.kutlu.kickstatz.util;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.kutlu.kickstatz.model.ResponsiveImageObject;
import com.kutlu.kickstatz.model.ResponsiveImages;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ResponsiveImagesDeserializer extends JsonDeserializer<ResponsiveImages> {

    @Override
    public ResponsiveImages deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        ResponsiveImages responsiveImages = new ResponsiveImages();

        if (node.isArray()) {
            responsiveImages.setFullsize(Collections.emptyList());
        } else if (node.isObject()) {
            JsonNode fullsizeNode = node.get("fullsize");

            if (fullsizeNode != null && fullsizeNode.isObject()) {
                ResponsiveImageObject fullsize = codec.treeToValue(fullsizeNode, ResponsiveImageObject.class);
                responsiveImages.setFullsize(List.of(fullsize));
            } else {
                responsiveImages.setFullsize(Collections.emptyList());
            }
        } else {
            responsiveImages.setFullsize(Collections.emptyList());
        }

        return responsiveImages;
    }
}