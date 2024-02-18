package com.jawad.restassuredspring3functionaltests;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import wiremock.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
class FunctionalTests {

    @LocalServerPort
    private int port = 0;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldReturnBankHolidaysForUK() {
        stubFor(get(urlEqualTo("/bank-holidays.json"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(FixtureReader.readFileContent("bank-holidays-response.json"))));

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
        stubFor(get(urlEqualTo("/bank-holidays.json"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(500)));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .get("/uk/bank-holidays")
                .then()
                .statusCode(500)
                .body("message", equalTo("Bank holidays api did not respond correctly, 500 Server Error: [no body]"),
                        "code", equalTo("SERVICE_IS_DOWN"));
    }
}
