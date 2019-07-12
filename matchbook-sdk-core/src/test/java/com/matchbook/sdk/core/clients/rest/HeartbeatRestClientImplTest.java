package com.matchbook.sdk.core.clients.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.matchbook.sdk.core.MatchbookSDKClientTest;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.ActionPerformed;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.HeartbeatSendRequest;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.HeartbeatUnsubscribeRequest;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;

public class HeartbeatRestClientImplTest extends MatchbookSDKClientTest {
    private final HeartbeatRestClient heartbeatRestClient;

    public HeartbeatRestClientImplTest() {
        this.heartbeatRestClient = new HeartbeatRestClientImpl(connectionManager);
    }

    @Test
    public void successfulGetHeartbeat() throws InterruptedException {
        stubFor(get(urlEqualTo("/edge/rest/v1/heartbeat"))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("matchbook/getHeartbeatSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        HeartbeatGetRequest heartbeatGetRequest = new HeartbeatGetRequest();
        heartbeatRestClient.getHeartbeat(heartbeatGetRequest, new StreamObserver<Heartbeat>() {
            @Override
            public void onNext(Heartbeat heartbeatResponse) {
                assertThat(heartbeatResponse.getActionPerformed()).isEqualTo(ActionPerformed.HEARTBEAT_ACTIVATED);
                assertThat(heartbeatResponse.getActualTimeout()).isNull();
                assertThat(heartbeatResponse.getTimeoutTime().toGMTString()).isEqualTo("12 Jul 2019 10:01:00 GMT");
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

        boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    @Test
    public void successfulSendHeartbeat() throws InterruptedException {
        stubFor(post(urlEqualTo("/edge/rest/v1/heartbeat"))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("matchbook/sendHeartbeatSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        HeartbeatSendRequest heartbeatSendRequest = new HeartbeatSendRequest.Builder(20).build();
        heartbeatRestClient.sendHeartbeat(heartbeatSendRequest, new StreamObserver<Heartbeat>() {
            @Override
            public void onNext(Heartbeat heartbeatResponse) {
                assertThat(heartbeatResponse.getActionPerformed()).isEqualTo(ActionPerformed.HEARTBEAT_ACTIVATED);
                assertThat(heartbeatResponse.getActualTimeout()).isEqualTo(20);
                assertThat(heartbeatResponse.getTimeoutTime().toGMTString()).isEqualTo("12 Jul 2019 10:01:00 GMT");
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

        boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    @Test
    public void successfulUnsubscribeHeartbeat() throws InterruptedException {
        stubFor(delete(urlEqualTo("/edge/rest/v1/heartbeat"))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("matchbook/unsubscribeHeartbeatSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        HeartbeatUnsubscribeRequest heartbeatUnsubscribeRequest = new HeartbeatUnsubscribeRequest();
        heartbeatRestClient.unsubscribeHeartbeat(heartbeatUnsubscribeRequest, new StreamObserver<Heartbeat>() {
            @Override
            public void onNext(Heartbeat heartbeatResponse) {
                assertThat(heartbeatResponse.getActionPerformed()).isEqualTo(ActionPerformed.HEARTBEAT_TERMINATED);
                assertThat(heartbeatResponse.getActualTimeout()).isNull();
                assertThat(heartbeatResponse.getTimeoutTime()).isNull();
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

        boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }
}