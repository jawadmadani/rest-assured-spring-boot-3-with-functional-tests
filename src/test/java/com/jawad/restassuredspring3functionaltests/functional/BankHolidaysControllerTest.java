package com.jawad.restassuredspring3functionaltests.functional;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
public class BankHolidaysControllerTest extends TestsConfig {

    @Test
    void shouldReturnBankHolidaysForUK() {
        stubBankHolidaysApiUp();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .get("/uk/bank-holidays")
                .then()
                .statusCode(200)
                .body("england-and-wales.division", equalTo("england-and-wales"),
                        "england-and-wales.events.size()", equalTo(75));
    }

    @Test
    void shouldReturn500InternalServerErrorWhenBankHolidaysApiIsDown() {
        stubBankHolidaysApiDown();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .get("/uk/bank-holidays")
                .then()
                .statusCode(500)
                .body("message", equalTo("Bank holidays api did not respond correctly, 500 Server Error: [no body]"),
                        "code", equalTo("SERVICE_IS_DOWN"));
    }
}
