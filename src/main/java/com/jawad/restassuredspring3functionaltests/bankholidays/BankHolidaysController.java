package com.jawad.restassuredspring3functionaltests.bankholidays;

import com.jawad.restassuredspring3functionaltests.BaseController;
import com.jawad.restassuredspring3functionaltests.bankholidays.io.BankHolidaysResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankHolidaysController extends BaseController {

    private final BankHolidaysService service;

    public BankHolidaysController(BankHolidaysService service) {
        this.service = service;
    }

    @GetMapping(path = "/bank-holidays")
    public ResponseEntity<BankHolidaysResponse> retrieveBankHolidays() {
        BankHolidaysResponse bankHolidaysResponse = service.callBankHolidaysApi();
        return new ResponseEntity<>(bankHolidaysResponse, HttpStatus.OK);
    }
}
