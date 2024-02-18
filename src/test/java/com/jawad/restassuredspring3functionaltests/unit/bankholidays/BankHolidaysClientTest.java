package com.jawad.restassuredspring3functionaltests.unit.bankholidays;

import com.jawad.restassuredspring3functionaltests.FixtureReader;
import com.jawad.restassuredspring3functionaltests.GeneralException;
import com.jawad.restassuredspring3functionaltests.bankholidays.BankHolidaysClient;
import com.jawad.restassuredspring3functionaltests.bankholidays.io.BankHolidaysResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankHolidaysClientTest {

    private BankHolidaysClient client;

    @Mock
    private RestTemplate mockRestTemplate;

    @BeforeEach
    void setUp() {
        client = new BankHolidaysClient(mockRestTemplate);
    }

    @Test
    void shouldReturn200BankHolidaysResponse() {
        ReflectionTestUtils.setField(client, "bankHolidaysUrl", "http://localhost/bank-holidays.json");
        when(mockRestTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(BankHolidaysResponse.class))).thenReturn(createBankHolidaysResponse());

        BankHolidaysResponse response = client.makeCallToBankHolidaysApi();

        assertEquals("england-and-wales", response.getEnglandAndWales().getDivision());
        assertEquals("New Year’s Day", response.getEnglandAndWales().getEvents().getFirst().getTitle());
        assertEquals("2018-01-01", response.getEnglandAndWales().getEvents().getFirst().getDate());
    }

    @Test
    void shouldThrow500InternalServerErrorException() {
        ReflectionTestUtils.setField(client, "bankHolidaysUrl", "http://localhost:${wiremock.server.port}/bank-holidays.json");
        when(mockRestTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(BankHolidaysResponse.class))).thenThrow(HttpServerErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", new HttpHeaders(), null, null));

        GeneralException generalException = assertThrows(GeneralException.class, () -> client.makeCallToBankHolidaysApi());

        assertEquals("Bank holidays api did not respond correctly, 500 Internal server error", generalException.getMessage());
    }

    private ResponseEntity<BankHolidaysResponse> createBankHolidaysResponse() {
        BankHolidaysResponse bankHolidaysResponse = FixtureReader.get("bank-holidays-response.json", BankHolidaysResponse.class);
        return new ResponseEntity<>(bankHolidaysResponse, HttpStatus.OK);
    }
}
