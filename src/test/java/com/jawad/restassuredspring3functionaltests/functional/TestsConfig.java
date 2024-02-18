package com.jawad.restassuredspring3functionaltests.functional;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.jawad.restassuredspring3functionaltests.FixtureReader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class TestsConfig {

    @LocalServerPort
    private int port = 0;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    protected void stubBankHolidaysApiUp() {
        stubFor(get(urlEqualTo("/bank-holidays.json"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(FixtureReader.readFileContent("bank-holidays-response.json"))));
    }

    protected void stubBankHolidaysApiDown() {
        stubFor(get(urlEqualTo("/bank-holidays.json"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(500)));
    }

    protected void stubIpInformationApiUp() {
        stubFor(get(urlEqualTo("/ipinfo.io/json"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(FixtureReader.readFileContent("ip-information-response.json"))));
    }

    protected void stubIpInformationApiDown() {
        stubFor(get(urlEqualTo("/ipinfo.io/json"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(500)));
    }
}
