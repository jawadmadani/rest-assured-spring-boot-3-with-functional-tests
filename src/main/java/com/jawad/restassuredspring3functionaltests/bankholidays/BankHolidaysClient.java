package com.jawad.restassuredspring3functionaltests.bankholidays;

import com.jawad.restassuredspring3functionaltests.bankholidays.io.BankHolidaysResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BankHolidaysClient {

    private final RestTemplate restTemplate;

    @Value("${bank-holidays.url}")
    private String bankHolidaysUrl;

    public BankHolidaysClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BankHolidaysResponse makeCallToBankHolidaysApi() {
        try {
            String url = bankHolidaysUrl;
            ResponseEntity<BankHolidaysResponse> response = restTemplate.exchange(url, HttpMethod.GET, createHttpHeaders(), BankHolidaysResponse.class);
            return response.getBody();
        } catch (Exception e) {
            throw new GeneralException("Bank holidays api did not respond correctly, " + e.getMessage(), e);
        }
    }

    private HttpEntity<HttpHeaders> createHttpHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(httpHeaders);
    }
}
