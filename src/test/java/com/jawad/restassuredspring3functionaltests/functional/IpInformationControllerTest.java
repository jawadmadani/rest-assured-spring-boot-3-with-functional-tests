package com.jawad.restassuredspring3functionaltests.functional;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.equalTo;

@ActiveProfiles("test")
public class IpInformationControllerTest extends TestsConfig {

    @Test
    void shouldReturnIpInformation() {
        stubIpInformationApiUp();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .get("/uk/ip-information")
                .then()
                .statusCode(200)
                .body("ip", equalTo("12.7.54.763"),
                        "city", equalTo("London"),
                        "country", equalTo("GB"));
    }

    @Test
    void shouldReturn500WhenIpInformationIsDown() {
        stubIpInformationApiDown();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .get("/uk/ip-information")
                .then()
                .statusCode(500)
                .body("message", Matchers.equalTo("Ip-information api is down, 500 Server Error: [no body]"),
                        "code", Matchers.equalTo("SERVICE_IS_DOWN"));;
    }
}
