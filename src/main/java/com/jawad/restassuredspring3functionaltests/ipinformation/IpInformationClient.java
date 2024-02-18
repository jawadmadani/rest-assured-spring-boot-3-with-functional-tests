package com.jawad.restassuredspring3functionaltests.ipinformation;

import com.jawad.restassuredspring3functionaltests.GeneralException;
import com.jawad.restassuredspring3functionaltests.ipinformation.io.IpInformationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IpInformationClient {

    private final RestTemplate restTemplate;

    @Value("${ip-information.url}")
    private String ipInformationUrl;

    public IpInformationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public IpInformationResponse callIpInformationApi() {
        try {
            String url = ipInformationUrl;
            ResponseEntity<IpInformationResponse> response = restTemplate.exchange(url, HttpMethod.GET, createHttpHeaders(), IpInformationResponse.class);
            return response.getBody();
        } catch (Exception e) {
            throw new GeneralException("Ip-information api is down, " + e.getMessage(), e);
        }
    }

    private HttpEntity<HttpHeaders> createHttpHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(httpHeaders);
    }
}
