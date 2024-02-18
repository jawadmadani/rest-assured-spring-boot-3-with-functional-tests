package com.jawad.restassuredspring3functionaltests.bankholidays;

import com.jawad.restassuredspring3functionaltests.bankholidays.io.BankHolidaysResponse;
import com.jawad.restassuredspring3functionaltests.bankholidays.io.ServiceInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankHolidaysController {

    private final BankHolidaysService service;

    public BankHolidaysController(BankHolidaysService service) {
        this.service = service;
    }

    @GetMapping(path = "/bank-holidays")
    public ResponseEntity<BankHolidaysResponse> retrieveBankHolidays() {
        BankHolidaysResponse bankHolidaysResponse = service.callBankHolidaysApi();
        return new ResponseEntity<>(bankHolidaysResponse, HttpStatus.OK);
    }

    @ExceptionHandler({GeneralException.class})
    public ResponseEntity<ServiceInfo> handleExceptions(GeneralException exception) {
        return new ResponseEntity<>(new ServiceInfo(exception.getMessage(), "SERVICE_IS_DOWN"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
