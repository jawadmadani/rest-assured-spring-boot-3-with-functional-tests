package com.jawad.restassuredspring3functionaltests.bankholidays;

import com.jawad.restassuredspring3functionaltests.FixtureReader;
import com.jawad.restassuredspring3functionaltests.bankholidays.io.BankHolidaysResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankHolidaysServiceTest {

    private BankHolidaysService service;

    @Mock
    private BankHolidaysClient mockBankHolidaysClient;

    @BeforeEach
    void setUp() {
        service = new BankHolidaysService(mockBankHolidaysClient);
    }

    @Test
    void callBankHolidaysApi() {
        when(mockBankHolidaysClient.makeCallToBankHolidaysApi()).thenReturn(createBankHolidaysResponse());
        BankHolidaysResponse response = service.callBankHolidaysApi();

        // verify the number of times it calls this method in a single execution
        verify(mockBankHolidaysClient, times(1)).makeCallToBankHolidaysApi();

        assertEquals("england-and-wales", response.englandAndWales.division);
        assertEquals("New Year’s Day", response.englandAndWales.events.getFirst().title);
        assertEquals("2018-01-01", response.englandAndWales.events.getFirst().date);
    }

    private BankHolidaysResponse createBankHolidaysResponse() {
        return FixtureReader.get("bank-holidays-response.json", BankHolidaysResponse.class);
    }
}
