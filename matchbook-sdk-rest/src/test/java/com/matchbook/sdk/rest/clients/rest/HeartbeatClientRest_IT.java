package com.matchbook.sdk.rest.clients.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.HeartbeatClientRest;
import com.matchbook.sdk.rest.MatchbookSDKClientRest_IT;
import com.matchbook.sdk.rest.dtos.heartbeat.ActionPerformed;
import com.matchbook.sdk.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatSendRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatUnsubscribeRequest;
import org.junit.Before;
import org.junit.Test;

public class HeartbeatClientRest_IT extends MatchbookSDKClientRest_IT {

    private SimpleDateFormat dateFormat;
    private HeartbeatClientRest heartbeatClientRest;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        this.heartbeatClientRest = new HeartbeatClientRest(connectionManager);
        this.dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        this.dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void getHeartbeatTest() throws InterruptedException, ParseException {
        wireMockServer.stubFor(get(urlEqualTo("/edge/rest/v1/heartbeat"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/heartbeat/getHeartbeatSuccessfulResponse.json")));
        Instant expectedHBTimeout = dateFormat.parse("12 Jul 2019 10:01:00").toInstant();

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        HeartbeatGetRequest heartbeatGetRequest = new HeartbeatGetRequest.Builder().build();
        heartbeatClientRest.getHeartbeat(heartbeatGetRequest, new StreamObserver<Heartbeat>() {
            @Override
            public void onNext(Heartbeat heartbeat) {
                assertNotNull(heartbeat);
                assertEquals(ActionPerformed.HEARTBEAT_ACTIVATED, heartbeat.getActionPerformed());
                assertEquals(expectedHBTimeout, heartbeat.getTimeoutTime());
                assertThat(heartbeat.getActualTimeout()).isNull();
                countDownLatch.countDown();
            }

            @Override
            public void onError(MatchbookSDKException e) {
                fail();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });

        boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    @Test
    public void sendHeartbeatTest() throws InterruptedException, ParseException {
        wireMockServer.stubFor(post(urlEqualTo("/edge/rest/v1/heartbeat"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/heartbeat/postHeartbeatSuccessfulResponse.json")));
        Instant expectedHBTimeout = dateFormat.parse("12 Jul 2019 10:01:00").toInstant();

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        HeartbeatSendRequest heartbeatSendRequest = new HeartbeatSendRequest.Builder(20).build();
        heartbeatClientRest.sendHeartbeat(heartbeatSendRequest, new StreamObserver<Heartbeat>() {
            @Override
            public void onNext(Heartbeat heartbeat) {
                assertNotNull(heartbeat);
                assertEquals(ActionPerformed.HEARTBEAT_ACTIVATED, heartbeat.getActionPerformed());
                assertEquals(20, heartbeat.getActualTimeout().intValue());
                assertEquals(expectedHBTimeout, heartbeat.getTimeoutTime());
                countDownLatch.countDown();
            }

            @Override
            public void onError(MatchbookSDKException e) {
                fail();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });

        boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    @Test
    public void unsubscribeHeartbeatTest() throws InterruptedException {
        wireMockServer.stubFor(delete(urlEqualTo("/edge/rest/v1/heartbeat"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/heartbeat/deleteHeartbeatSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        HeartbeatUnsubscribeRequest heartbeatUnsubscribeRequest = new HeartbeatUnsubscribeRequest.Builder().build();
        heartbeatClientRest.unsubscribeHeartbeat(heartbeatUnsubscribeRequest, new StreamObserver<Heartbeat>() {
            @Override
            public void onNext(Heartbeat heartbeat) {
                assertNotNull(heartbeat);
                assertEquals(ActionPerformed.HEARTBEAT_TERMINATED, heartbeat.getActionPerformed());
                assertNull(heartbeat.getActualTimeout());
                assertNull(heartbeat.getTimeoutTime());
                countDownLatch.countDown();
            }

            @Override
            public void onError(MatchbookSDKException e) {
                fail();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });

        boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }
}
