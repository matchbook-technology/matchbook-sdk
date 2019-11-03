package com.matchbook.sdk.rest.workers;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.rest.configs.ClientConfig;
import com.matchbook.sdk.rest.configs.ConnectionManager;
import com.matchbook.sdk.rest.configs.HttpConfig;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("integration")
class SessionKeepAliveScheduler_IT {

    private static WireMockServer wireMockServer;

    private ConnectionManager connectionManager;
    private SessionKeepAliveScheduler sessionKeepAliveScheduler;

    @BeforeAll
    static void setUpBeforeClass() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8089));
        wireMockServer.start();
    }

    @AfterAll
    static void tearDownAfterClass() {
        wireMockServer.stop();
    }

    @BeforeEach
    void setUp() {
        HttpConfig httpConfig = new HttpConfig.Builder()
                .connectionTimeout(200L, TimeUnit.MILLISECONDS)
                .readTimeout(200L, TimeUnit.MILLISECONDS)
                .writeTimeout(200L, TimeUnit.MILLISECONDS)
                .build();
        ClientConfig clientConfig = new ClientConfig.Builder("login".toCharArray(), "password".toCharArray())
                .loginUrl("http://localhost:8089/bpapi/rest/security/session")
                .httpClientConfig(httpConfig)
                .build();
        connectionManager = new ConnectionManager.Builder(clientConfig)
                .autoManageSession(false)
                .build();

        sessionKeepAliveScheduler = new SessionKeepAliveScheduler(connectionManager);
    }

    @AfterEach
    void tearDown() {
        connectionManager.close();
    }

    @Test
    @DisplayName("Successfully login when scheduler starts")
    void loginOnStartTest() {
        wireMockServer.stubFor(post(urlPathEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/loginSuccessfulResponse.json")));

        sessionKeepAliveScheduler.start();

        assertThat(sessionKeepAliveScheduler.isStarted()).isTrue();
    }

    @Test
    @DisplayName("Fail login when scheduler starts")
    void failLoginOnStartTest() {
        wireMockServer.stubFor(post(urlPathEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(401)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/loginFailedResponse.json")));

        assertThatExceptionOfType(MatchbookSDKHttpException.class)
                .isThrownBy(() -> sessionKeepAliveScheduler.start())
                .withCauseExactlyInstanceOf(MatchbookSDKHttpException.class)
                .matches(e -> e.getErrorType().equals(ErrorType.UNAUTHENTICATED));
    }

    @Test
    @DisplayName("Login timeout when scheduler starts")
    void loginTimeoutOnStartTest() {
        wireMockServer.stubFor(post(urlPathEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withFixedDelay(500)
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/loginSuccessfulResponse.json")));

        assertThatExceptionOfType(MatchbookSDKHttpException.class)
                .isThrownBy(() -> sessionKeepAliveScheduler.start())
                .withCauseExactlyInstanceOf(TimeoutException.class)
                .matches(e -> e.getErrorType().equals(ErrorType.HTTP));
    }

}
