package com.jawad.restassuredspring3functionaltests.unit.ipinformation;

import com.jawad.restassuredspring3functionaltests.FixtureReader;
import com.jawad.restassuredspring3functionaltests.GeneralException;
import com.jawad.restassuredspring3functionaltests.ipinformation.IpInformationClient;
import com.jawad.restassuredspring3functionaltests.ipinformation.io.IpInformationResponse;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IpInformationClientTest {

    private IpInformationClient client;

    @Mock
    private RestTemplate mockRestTemplate;

    @BeforeEach
    void setUp() {
        client = new IpInformationClient(mockRestTemplate);
    }

    @Test
    void shouldReturnIpInformation() {
        ReflectionTestUtils.setField(client, "ipInformationUrl", "https://localhost/ipinfo.io/json");
        when(mockRestTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(IpInformationResponse.class))).thenReturn(createIpInformationResponse());
        IpInformationResponse response = client.callIpInformationApi();

        assertEquals("12.7.54.763", response.getIp());
        assertEquals("London", response.getCity());
        assertEquals("England", response.getRegion());
        assertEquals("GB", response.getCountry());
        assertEquals("L1", response.getPostal());
    }

    @Test
    void shouldThrow500InternalServerErrorException() {
        ReflectionTestUtils.setField(client, "ipInformationUrl", "https://localhost/ipinfo.io/json");
        when(mockRestTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(IpInformationResponse.class))).thenThrow(HttpServerErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", new HttpHeaders(), null, null));

        GeneralException generalException = assertThrows(GeneralException.class, () -> client.callIpInformationApi());

        assertEquals("Ip-information api is down, 500 Internal server error", generalException.getMessage());
    }

    private ResponseEntity<IpInformationResponse> createIpInformationResponse() {
        IpInformationResponse ipInformationResponse = FixtureReader.get("ip-information-response.json", IpInformationResponse.class);
        return new ResponseEntity<>(ipInformationResponse, HttpStatus.OK);
    }
}
