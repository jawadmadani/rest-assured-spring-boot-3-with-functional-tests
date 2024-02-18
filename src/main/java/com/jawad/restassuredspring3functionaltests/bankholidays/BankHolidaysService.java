package com.jawad.restassuredspring3functionaltests.bankholidays;

import com.jawad.restassuredspring3functionaltests.bankholidays.io.BankHolidaysResponse;
import org.springframework.stereotype.Service;

@Service
public class BankHolidaysService {

    private final BankHolidaysClient client;

    public BankHolidaysService(BankHolidaysClient client) {
        this.client = client;
    }

    public BankHolidaysResponse callBankHolidaysApi() {
        return client.makeCallToBankHolidaysApi();
    }
}
