package com.jawad.restassuredspring3functionaltests.ipinformation.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jawad.restassuredspring3functionaltests.bankholidays.io.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IpInformationResponse extends BaseModel {

    @JsonProperty("ip")
    private String ip;
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("city")
    private String city;
    @JsonProperty("region")
    private String region;
    @JsonProperty("country")
    private String country;
    @JsonProperty("loc")
    private String loc;
    @JsonProperty("org")
    private String org;
    @JsonProperty("postal")
    private String postal;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("readme")
    private String readme;
}
