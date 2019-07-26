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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.matchbook.sdk.common.StreamObserver;
import com.matchbook.sdk.common.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.HeartbeatClient;
import com.matchbook.sdk.core.HeartbeatClientRest;
import com.matchbook.sdk.core.MatchbookSDKClientTest;
import com.matchbook.sdk.core.dtos.heartbeat.ActionPerformed;
import com.matchbook.sdk.core.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.core.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.core.dtos.heartbeat.HeartbeatSendRequest;
import com.matchbook.sdk.core.dtos.heartbeat.HeartbeatUnsubscribeRequest;
import org.junit.Test;

public class HeartbeatClientRestTest extends MatchbookSDKClientTest {

    private final HeartbeatClient heartbeatClient;
    private final SimpleDateFormat dateFormat;

    public HeartbeatClientRestTest() {
        this.heartbeatClient = new HeartbeatClientRest(connectionManager);
        this.dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss 'GMT'");
        this.dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void successfulGetHeartbeat() throws InterruptedException, ParseException {
        stubFor(get(urlEqualTo("/edge/rest/v1/heartbeat"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/getHeartbeatSuccessfulResponse.json")));
        Date expectedHBTimeout = dateFormat.parse("12 Jul 2019 10:01:00 GMT");

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        HeartbeatGetRequest heartbeatGetRequest = new HeartbeatGetRequest.Builder().build();
        heartbeatClient.getHeartbeat(heartbeatGetRequest, new StreamObserver<Heartbeat>() {
            @Override
            public void onNext(Heartbeat heartbeatResponse) {
                assertThat(heartbeatResponse.getActionPerformed()).isEqualTo(ActionPerformed.HEARTBEAT_ACTIVATED);
                assertThat(heartbeatResponse.getActualTimeout()).isNull();
                assertThat(heartbeatResponse.getTimeoutTime()).isEqualTo(expectedHBTimeout);
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
    public void successfulSendHeartbeat() throws InterruptedException, ParseException {
        stubFor(post(urlEqualTo("/edge/rest/v1/heartbeat"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/sendHeartbeatSuccessfulResponse.json")));
        Date expectedHBTimeout = dateFormat.parse("12 Jul 2019 10:01:00 GMT");

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        HeartbeatSendRequest heartbeatSendRequest = new HeartbeatSendRequest.Builder(20).build();
        heartbeatClient.sendHeartbeat(heartbeatSendRequest, new StreamObserver<Heartbeat>() {
            @Override
            public void onNext(Heartbeat heartbeatResponse) {
                assertThat(heartbeatResponse.getActionPerformed()).isEqualTo(ActionPerformed.HEARTBEAT_ACTIVATED);
                assertThat(heartbeatResponse.getActualTimeout()).isEqualTo(20);
                assertThat(heartbeatResponse.getTimeoutTime()).isEqualTo(expectedHBTimeout);
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
        HeartbeatUnsubscribeRequest heartbeatUnsubscribeRequest = new HeartbeatUnsubscribeRequest.Builder().build();
        heartbeatClient.unsubscribeHeartbeat(heartbeatUnsubscribeRequest, new StreamObserver<Heartbeat>() {
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
