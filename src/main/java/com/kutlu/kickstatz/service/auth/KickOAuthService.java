package com.kutlu.kickstatz.service.auth;
import com.kutlu.kickstatz.model.KickTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KickOAuthService {

    private final RestTemplate restTemplate;
    private final String clientId;
    private final String clientSecret;
    private final String tokenUrl;

    public KickOAuthService(RestTemplate restTemplate,
                        @Value("${kick.client-id}") String clientId,
                        @Value("${kick.client-secret}") String clientSecret,
                        @Value("${kick.token-url}") String tokenUrl) {
        this.restTemplate = restTemplate;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenUrl = tokenUrl;
    }

    public String getAppAccessToken() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<KickTokenResponse> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, KickTokenResponse.class);
        return extractAccessToken(response);
    }

    private String extractAccessToken(ResponseEntity<KickTokenResponse> response) {
        return response.getBody() != null ? response.getBody().getAccessToken() : null;
    }

}