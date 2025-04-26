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

    public ChannelResponse getChannelBySlug(String slug) {
        String url = baseUrl + "/channels/" + slug;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> entity = new HttpEntity<>(headers);

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
