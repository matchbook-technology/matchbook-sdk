package com.matchbook.sdk.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.rest.dtos.heartbeat.ActionPerformed;
import com.matchbook.sdk.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatPostRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatDeleteRequest;

import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("integration")
public class HeartbeatClientRest_IT extends MatchbookSDKClientRest_IT<HeartbeatClientRest> {

    @Override
    protected HeartbeatClientRest newClientRest(ConnectionManager connectionManager) {
        return new HeartbeatClientRest(connectionManager);
    }

    @Test
    @DisplayName("Check heartbeat")
    void getHeartbeatTest() {
        String url = "/edge/rest/v1/heartbeat";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/heartbeat/getHeartbeatSuccessfulResponse.json")));

        HeartbeatGetRequest heartbeatGetRequest = new HeartbeatGetRequest.Builder().build();
        ResponseStreamObserver<Heartbeat> streamObserver = new SuccessfulResponseStreamObserver<>(1, heartbeat -> {
            assertThat(heartbeat).isNotNull();
            assertThat(heartbeat.getActionPerformed()).isEqualTo(ActionPerformed.HEARTBEAT_ACTIVATED);
            assertThat(heartbeat.getTimeoutTime()).isCloseTo("2019-07-12T10:01:00Z", within(1, ChronoUnit.SECONDS));
            assertThat(heartbeat.getActualTimeout()).isNull();
        });
        clientRest.getHeartbeat(heartbeatGetRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    @DisplayName("Send heartbeat")
    void sendHeartbeatTest() {
        String url = "/edge/rest/v1/heartbeat";
        wireMockServer.stubFor(post(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/heartbeat/postHeartbeatSuccessfulResponse.json")));

        HeartbeatPostRequest heartbeatPostRequest = new HeartbeatPostRequest.Builder(20).build();
        ResponseStreamObserver<Heartbeat> streamObserver = new SuccessfulResponseStreamObserver<>(1, heartbeat -> {
            assertThat(heartbeat).isNotNull();
            assertThat(heartbeat.getActionPerformed()).isEqualTo(ActionPerformed.HEARTBEAT_ACTIVATED);
            assertThat(heartbeat.getTimeoutTime()).isCloseTo("2019-07-12T10:01:00Z", within(1, ChronoUnit.SECONDS));
            assertThat(heartbeat.getActualTimeout()).isEqualTo(20);
        });
        clientRest.sendHeartbeat(heartbeatPostRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    @DisplayName("Unsubscribe from heartbeat")
    void unsubscribeHeartbeatTest() {
        String url = "/edge/rest/v1/heartbeat";
        wireMockServer.stubFor(delete(urlPathEqualTo("/edge/rest/v1/heartbeat"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/heartbeat/deleteHeartbeatSuccessfulResponse.json")));

        HeartbeatDeleteRequest heartbeatDeleteRequest = new HeartbeatDeleteRequest.Builder().build();
        ResponseStreamObserver<Heartbeat> streamObserver = new SuccessfulResponseStreamObserver<>(1, heartbeat -> {
            assertThat(heartbeat).isNotNull();
            assertThat(heartbeat.getActionPerformed()).isEqualTo(ActionPerformed.HEARTBEAT_TERMINATED);
            assertThat(heartbeat.getTimeoutTime()).isNull();
            assertThat(heartbeat.getActualTimeout()).isNull();
        });
        clientRest.unsubscribeHeartbeat(heartbeatDeleteRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(deleteRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    @DisplayName("Unexpected server error in heartbeat API")
    void unexpectedServerErrorTest() {
        String url = "/edge/rest/v1/heartbeat";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/serverErrorResponse.json")));

        HeartbeatGetRequest heartbeatGetRequest = new HeartbeatGetRequest.Builder().build();
        ResponseStreamObserver<Heartbeat> streamObserver = new FailedResponseStreamObserver<>(exception -> {
            assertThat(exception).isNotNull();
            assertThat(exception.getErrorType()).isEqualTo(ErrorType.HTTP);
        });
        clientRest.getHeartbeat(heartbeatGetRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    @DisplayName("Unexpected empty response in heartbeat API")
    void unexpectedEmptyResponseTest() {
        String url = "/edge/rest/v1/heartbeat";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("")));

        HeartbeatGetRequest heartbeatGetRequest = new HeartbeatGetRequest.Builder().build();
        ResponseStreamObserver<Heartbeat> streamObserver = new FailedResponseStreamObserver<>(exception -> {
            assertThat(exception).isNotNull();
            assertThat(exception.getErrorType()).isEqualTo(ErrorType.HTTP);
        });
        clientRest.getHeartbeat(heartbeatGetRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

}
