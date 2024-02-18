package com.jawad.restassuredspring3functionaltests.unit.ipinformation;

import com.jawad.restassuredspring3functionaltests.FixtureReader;
import com.jawad.restassuredspring3functionaltests.ipinformation.IpInformationClient;
import com.jawad.restassuredspring3functionaltests.ipinformation.IpInformationService;
import com.jawad.restassuredspring3functionaltests.ipinformation.io.IpInformationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IpInformationServiceTest {

    private IpInformationService service;

    @Mock
    private IpInformationClient mockIpInformationClient;

    @BeforeEach
    void setUp() {
        service = new IpInformationService(mockIpInformationClient);
    }

    @Test
    void shouldCallClientOneTime() {
        when(mockIpInformationClient.callIpInformationApi()).thenReturn(createIpInformationResponse());
        IpInformationResponse response = service.callIpInformationClient();

        // verify the number of times it calls this method in a single execution
        verify(mockIpInformationClient, times(1)).callIpInformationApi();

        assertEquals("12.7.54.763", response.getIp());
        assertEquals("London", response.getCity());
        assertEquals("England", response.getRegion());
        assertEquals("GB", response.getCountry());
        assertEquals("L1", response.getPostal());
    }

    private IpInformationResponse createIpInformationResponse() {
        return FixtureReader.get("ip-information-response.json", IpInformationResponse.class);
    }
}
