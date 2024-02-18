package com.jawad.restassuredspring3functionaltests.bankholidays.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event extends BaseModel {

    @JsonProperty("title")
    private String title;
    @JsonProperty("date")
    private String date;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("bunting")
    private boolean bunting;

}
