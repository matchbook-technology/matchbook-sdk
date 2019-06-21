package com.matchbook.sdk.core.services;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.matchbook.sdk.core.MatchbookSDKClientTest;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.model.dataobjects.LoginEnvelope;
import com.matchbook.sdk.core.model.dataobjects.auth.Credentials;

public class AuthRestServiceTest extends MatchbookSDKClientTest {

    private static final char[] USERNAME = "username".toCharArray();
    private static final char[] PASSWORD = "password".toCharArray();
    private static final String LOGIN_URL = "/bpapi/rest/security/session";

    private AuthRestService unit;

    @Before
    public void setUp() throws Exception {
        this.unit = new AuthRestService(clientConnectionManager);
    }

    @Test
    public void login() throws InterruptedException {
        stubLoginResponse();

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        Credentials credentials = new Credentials.Builder(USERNAME, PASSWORD).build();
        unit.login(credentials, new StreamObserver<LoginEnvelope>() {
            @Override
            public void onNext(LoginEnvelope loginEnvelope) {
                assertThat(loginEnvelope.getUser().getUserId()).isNotZero();
                assertThat(loginEnvelope.getUser().getAccount()).isNotNull();
                assertThat(loginEnvelope.getUser().getSessionToken()).isNotNull();
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
    public void sessionToken() {
        stubLoginResponse();
        String sessiontoken = unit.sessionToken(USERNAME, PASSWORD);
        verify(1, postRequestedFor(urlEqualTo(LOGIN_URL)));
        assertThat(sessiontoken).isEqualTo("000000_8beb227cee56ae515f7e312330456789");
    }

    @Test
    public void invalidateSessionToken_NewSessionRequestedAfterInvalidation() {
        stubLoginResponse();
        unit.sessionToken(USERNAME, PASSWORD);

        unit.invalidateSessionToken();

        unit.sessionToken(USERNAME, PASSWORD);

        verify(2, postRequestedFor(urlEqualTo(LOGIN_URL)));
    }

    @Test
    public void invalidateSessionToken_NoSessionRequestedIfNoInvalidation() {
        stubLoginResponse();
        unit.sessionToken(USERNAME, PASSWORD);

        unit.sessionToken(USERNAME, PASSWORD);

        verify(1, postRequestedFor(urlEqualTo(LOGIN_URL)));
    }

    private void stubLoginResponse() {
        stubFor(post(urlEqualTo(LOGIN_URL))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("matchbook/loginSuccessfulResponse.json")));
    }
}