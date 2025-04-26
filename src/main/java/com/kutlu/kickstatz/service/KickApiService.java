package com.kutlu.kickstatz.service;

import com.kutlu.kickstatz.model.ChannelResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.7049.115 Safari/537.36");
        headers.set("Accept", "*/*");
        headers.set("Cache-Control", "no-cache");
        headers.set("Accept-Encoding", "identity");
        headers.set("Connection", "keep-alive");
        headers.set("Cookie", ""); // boş bile olsa ekleyelim
        return headers;
    }

    public ChannelResponse getChannelBySlug(String slug) {
        String url = baseUrl + "/channels/" + slug;

        HttpEntity<Void> entity = new HttpEntity<>(buildHeaders());

        try {
            ResponseEntity<ChannelResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    ChannelResponse.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
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
