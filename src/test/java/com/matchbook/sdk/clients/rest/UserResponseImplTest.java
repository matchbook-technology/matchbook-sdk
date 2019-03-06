package com.matchbook.sdk.clients.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.matchbook.sdk.MatchbookSDKClientTest;
import com.matchbook.sdk.StreamObserver;
import com.matchbook.sdk.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.clients.rest.dtos.user.LoginResponse;
import com.matchbook.sdk.exceptions.MatchbookSDKException;
import org.junit.Test;

public class UserResponseImplTest extends MatchbookSDKClientTest {

    private final UserResource userResource;

    public UserResponseImplTest() {
        this.userResource = new UserResponseImpl(clientConnectionManager);
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {

        stubFor(post(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/loginResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        LoginRequest loginRequest = new LoginRequest("username".toCharArray(), "password".toCharArray());
        userResource.login(loginRequest, new StreamObserver<LoginResponse>() {
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
