package com.kutlu.kickstatz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kutlu.kickstatz.model.ChannelDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class to interact with the Kick API.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class KickApiService {

    private final RestTemplate restTemplate;

    @Value("${kick.api.base-url}")
    private String baseUrl;

    @Value("${kick.api.cookie}")
    private String cookie;

    public HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");        //headers.set("Accept-Encoding", "deflate, br, zstd");
        headers.set("Accept-Language", "en-US,en;q=0.9");
        headers.set("Cache-Control", "max-age=0");
        headers.set("Cookie", cookie);
        headers.set("Priority", "u=0, i");
        headers.set("Sec-Ch-Ua", "\"Microsoft Edge\";v=\"135\", \"Not-A.Brand\";v=\"8\", \"Chromium\";v=\"135\"");
        headers.set("Sec-Ch-Ua-Mobile", "?0");
        headers.set("Sec-Ch-Ua-Platform", "\"Windows\"");
        headers.set("Sec-Fetch-Dest", "document");
        headers.set("Sec-Fetch-Mode", "navigate");
        headers.set("Sec-Fetch-Site", "none");
        headers.set("Sec-Fetch-User", "?1");
        headers.set("Upgrade-Insecure-Requests", "1");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Safari/537.36 Edg/135.0.0.0");

        return headers;
    }

    public ChannelDetailResponse getChannelBySlug(String slug) {
        String url = baseUrl + "/channels/" + slug;

        HttpEntity<Void> entity = new HttpEntity<>(buildHeaders());

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String responseBody = response.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(responseBody, ChannelDetailResponse.class);
            } else {
                log.warn("No data found for slug: {}", slug);
                return null;
            }
        } catch (Exception e) {
            log.error("Error while fetching channel by slug '{}': {}", slug, e.getMessage(), e);
            return null;
        }
    }
}
