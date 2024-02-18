package com.jawad.restassuredspring3functionaltests.bankholidays.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ServiceInfo extends BaseModel {

    private String message;
    private String code;
}
