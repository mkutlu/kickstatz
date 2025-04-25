package com.kutlu.kickstatz.crawler;

import com.kutlu.kickstatz.model.ChannelData;
import com.kutlu.kickstatz.model.ChannelResponse;
import com.kutlu.kickstatz.service.auth.KickOAuthService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ChannelCrawler {

    private static final Logger logger = LoggerFactory.getLogger(ChannelCrawler.class);

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${kick.channel.list.api}")
    private String channelListUrl;

    @Getter
    private List<String> currentOnlineChannels;

    private final KickOAuthService kickOAuthService;

    @Autowired
    public ChannelCrawler(KickOAuthService kickOAuthService) {
        this.kickOAuthService = kickOAuthService;
    }

    @Scheduled(fixedDelay = 60000)
    public void updateOnlineChannels() {
        logger.info("Starting to update online channels...");
        try {
            String accessToken = kickOAuthService.getAppAccessToken();
            HttpHeaders headers = new HttpHeaders();
            logger.info("Access Token: {}", accessToken);
            headers.set("Authorization", "Bearer " + accessToken);
            //headers.set("Authorization", accessToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<ChannelResponse> response = restTemplate.exchange(
                    channelListUrl,
                    HttpMethod.GET,
                    entity,
                    ChannelResponse.class
            );


            if (response.getBody() != null && response.getBody().getData() != null) {
                this.currentOnlineChannels = response.getBody().getData().stream()
                        .filter(Objects::nonNull)
                        .map(ChannelData::getSlug)
                        .collect(Collectors.toList());
                logger.info("Successfully updated online channels: {}", currentOnlineChannels);
            } else {
                logger.warn("Received null or empty response from the channel list API.");
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating online channels: {}", e.getMessage(), e);
        }
    }

}