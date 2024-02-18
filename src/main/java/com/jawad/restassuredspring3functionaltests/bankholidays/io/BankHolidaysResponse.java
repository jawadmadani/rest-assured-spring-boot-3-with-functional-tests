package com.jawad.restassuredspring3functionaltests.bankholidays.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankHolidaysResponse extends BaseModel {

    @JsonProperty("england-and-wales")
    private EnglandAndWales englandAndWales;
    @JsonProperty("scotland")
    private Scotland scotland;
    @JsonProperty("northern-ireland")
    private NorthernIreland northernIreland;
}
