package com.jawad.restassuredspring3functionaltests;


import com.jawad.restassuredspring3functionaltests.bankholidays.io.ServiceInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    @ExceptionHandler({GeneralException.class})
    public ResponseEntity<ServiceInfo> handleExceptions(GeneralException exception) {
        return new ResponseEntity<>(new ServiceInfo(exception.getMessage(), "SERVICE_IS_DOWN"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
