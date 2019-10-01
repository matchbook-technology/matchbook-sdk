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

import com.matchbook.sdk.rest.dtos.heartbeat.ActionPerformed;
import com.matchbook.sdk.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatSendRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatUnsubscribeRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

public class HeartbeatClientRest_IT extends MatchbookSDKClientRest_IT<HeartbeatClientRest> {

    private SimpleDateFormat dateFormat;

    @Override
    protected HeartbeatClientRest newClientRest(ConnectionManager connectionManager) {
        return new HeartbeatClientRest(connectionManager);
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();

        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void getHeartbeatTest() throws ParseException {
        String url = "/edge/rest/v1/heartbeat";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/heartbeat/getHeartbeatSuccessfulResponse.json")));

        HeartbeatGetRequest heartbeatGetRequest = new HeartbeatGetRequest.Builder().build();
        Instant expectedHeartbeatTimeout = dateFormat.parse("2019-07-12T10:01:00").toInstant();
        ResponseStreamObserver<Heartbeat> streamObserver = new SuccessfulResponseStreamObserver<>(1, heartbeat -> {
            assertThat(heartbeat).isNotNull();
            assertThat(heartbeat.getActionPerformed()).isEqualTo(ActionPerformed.HEARTBEAT_ACTIVATED);
            assertThat(heartbeat.getTimeoutTime()).isEqualTo(expectedHeartbeatTimeout);
            assertThat(heartbeat.getActualTimeout()).isNull();
        });
        clientRest.getHeartbeat(heartbeatGetRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void sendHeartbeatTest() throws ParseException {
        String url = "/edge/rest/v1/heartbeat";
        wireMockServer.stubFor(post(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/heartbeat/postHeartbeatSuccessfulResponse.json")));

        HeartbeatSendRequest heartbeatSendRequest = new HeartbeatSendRequest.Builder(20).build();
        Instant expectedHeartbeatTimeout = dateFormat.parse("2019-07-12T10:01:00").toInstant();
        ResponseStreamObserver<Heartbeat> streamObserver = new SuccessfulResponseStreamObserver<>(1, heartbeat -> {
            assertThat(heartbeat).isNotNull();
            assertThat(heartbeat.getActionPerformed()).isEqualTo(ActionPerformed.HEARTBEAT_ACTIVATED);
            assertThat(heartbeat.getTimeoutTime()).isEqualTo(expectedHeartbeatTimeout);
            assertThat(heartbeat.getActualTimeout()).isEqualTo(20);
        });
        clientRest.sendHeartbeat(heartbeatSendRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void unsubscribeHeartbeatTest() {
        String url = "/edge/rest/v1/heartbeat";
        wireMockServer.stubFor(delete(urlPathEqualTo("/edge/rest/v1/heartbeat"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/heartbeat/deleteHeartbeatSuccessfulResponse.json")));

        HeartbeatUnsubscribeRequest heartbeatUnsubscribeRequest = new HeartbeatUnsubscribeRequest.Builder().build();
        ResponseStreamObserver<Heartbeat> streamObserver = new SuccessfulResponseStreamObserver<>(1, heartbeat -> {
            assertThat(heartbeat).isNotNull();
            assertThat(heartbeat.getActionPerformed()).isEqualTo(ActionPerformed.HEARTBEAT_TERMINATED);
            assertThat(heartbeat.getTimeoutTime()).isNull();
            assertThat(heartbeat.getActualTimeout()).isNull();
        });
        clientRest.unsubscribeHeartbeat(heartbeatUnsubscribeRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(deleteRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

}
