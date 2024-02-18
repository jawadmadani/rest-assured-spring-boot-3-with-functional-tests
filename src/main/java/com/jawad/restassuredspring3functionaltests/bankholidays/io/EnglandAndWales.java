package com.jawad.restassuredspring3functionaltests.bankholidays.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnglandAndWales extends BaseModel {

    @JsonProperty("division")
    private String division;
    @JsonProperty("events")
    private ArrayList<Event> events;

}
