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
    public String title;
    @JsonProperty("date")
    public String date;
    @JsonProperty("notes")
    public String notes;
    @JsonProperty("bunting")
    public boolean bunting;

}
