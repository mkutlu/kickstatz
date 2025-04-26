package com.kutlu.kickstatz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * "media": [
 *         {
 *             "id": 53645955,
 *             "model_type": "App\\Models\\Channel",
 *             "model_id": 2340642,
 *             "collection_name": "banner_image",
 *             "name": "78ea2be5-6f45-4078-ac11-399a3e8e7b23",
 *             "file_name": "78ea2be5-6f45-4078-ac11-399a3e8e7b23",
 *             "mime_type": "image\/jpeg",
 *             "disk": "s3",
 *             "size": 747941,
 *             "manipulations": [],
 *             "custom_properties": {
 *                 "generated_conversions": {
 *                     "fullsize": true,
 *                     "medium": true
 *                 }
 *             },
 *             "responsive_images": [],
 *             "order_column": 28866257,
 *             "created_at": "2023-07-31T22:12:53.000000Z",
 *             "updated_at": "2023-07-31T22:12:54.000000Z",
 *             "uuid": "3fe3f0aa-12a8-4a78-a4f4-4d06a1f76b76",
 *             "conversions_disk": "s3"
 *         }
 *    ]
 */
@Data
public class Media {
    private int id;
    @JsonProperty("model_type")
    private String modelType;
    @JsonProperty("model_id")
    private int modelId;
    @JsonProperty("collection_name")
    private String collectionName;
    private String name;
    @JsonProperty("file_name")
    private String fileName;
    @JsonProperty("mime_type")
    private String mimeType;
    private String disk;
    private int size;
    private List<String> manipulations;
    @JsonProperty("custom_properties")
    private CustomProperty customProperties;
    @JsonProperty("responsive_images")
    private ResponsiveImages responsiveImages;
    @JsonProperty("order_column")
    private int orderColumn;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private String uuid;
    @JsonProperty("conversions_disk")
    private String conversionsDisk;
}
