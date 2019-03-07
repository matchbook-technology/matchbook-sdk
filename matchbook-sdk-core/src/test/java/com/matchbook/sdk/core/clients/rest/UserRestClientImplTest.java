package com.matchbook.sdk.core.clients.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.matchbook.sdk.core.MatchbookSDKClientTest;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginResponse;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import org.junit.Test;

public class UserRestClientImplTest extends MatchbookSDKClientTest {

    private final UserRestClient userRestClient;

    public UserRestClientImplTest() {
        this.userRestClient = new UserRestClientImpl(clientConnectionManager);
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {

        stubFor(post(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/loginSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        LoginRequest loginRequest = new LoginRequest.Builder("username".toCharArray(),
                "password".toCharArray())
                .build();
        userRestClient.login(loginRequest, new StreamObserver<LoginResponse>() {
            @Override
            public void onNext(LoginResponse loginResponse) {
                assertThat(loginResponse.getSessionToken()).isNotEmpty();
                assertThat(loginResponse.getUserId()).isNotZero();
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
