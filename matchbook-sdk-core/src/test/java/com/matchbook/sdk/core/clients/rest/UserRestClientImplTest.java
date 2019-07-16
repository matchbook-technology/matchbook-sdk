package com.matchbook.sdk.core.clients.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.matchbook.sdk.core.MatchbookSDKClientTest;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.user.Account;
import com.matchbook.sdk.core.clients.rest.dtos.user.AccountRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.Balance;
import com.matchbook.sdk.core.clients.rest.dtos.user.BalanceRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import org.junit.Test;

public class UserRestClientImplTest extends MatchbookSDKClientTest {

    private final UserRestClient userRestClient;

    public UserRestClientImplTest() {
        this.userRestClient = new UserRestClientImpl(connectionManager);
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
        LoginRequest loginRequest = new LoginRequest.Builder("username".toCharArray(), "password".toCharArray())
                .build();
        userRestClient.login(loginRequest, new StreamObserver<Login>() {
            @Override
            public void onNext(Login login) {
                assertThat(login.getSessionToken()).isNotEmpty();
                assertThat(login.getUserId()).isNotZero();
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
    public void incorrectUserPasswordLoginTest() throws InterruptedException {
        stubFor(post(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(401)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/loginFailedResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        LoginRequest loginRequest = new LoginRequest.Builder("wrongUser".toCharArray(), "wrongPassword".toCharArray())
                .build();
        userRestClient.login(loginRequest, new StreamObserver<Login>() {
            @Override
            public void onNext(Login login) {
                fail();
            }

            @Override
            public void onError(MatchbookSDKException e) {
                assertThat(e.getErrorType()).isEqualTo(ErrorType.UNAUTHENTICATED);
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                fail();
            }
        });

        boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    @Test
    public void emptyResponseBodyLoginTest() throws InterruptedException {
        stubFor(post(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("")));

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        LoginRequest loginRequest = new LoginRequest.Builder("wrongUser".toCharArray(), "wrongPassword".toCharArray())
                .build();
        userRestClient.login(loginRequest, new StreamObserver<Login>() {
            @Override
            public void onNext(Login login) {
                fail();
            }

            @Override
            public void onError(MatchbookSDKException e) {
                assertThat(e.getErrorType()).isEqualTo(ErrorType.HTTP);
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                fail();
            }
        });

        boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    @Test
    public void successfulGetAccountTest() throws InterruptedException {
        stubFor(get(urlPathEqualTo("/edge/rest/account"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/getAccountSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        AccountRequest accountRequest = new AccountRequest.Builder().build();

        userRestClient.getAccount(accountRequest, new StreamObserver<Account>() {
            @Override
            public void onNext(Account account) {
                assertThat(account.getUsername()).isNotEmpty();
                assertThat(account.getId()).isNotZero();
                assertThat(account.getBalance()).isPositive();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }

            @Override
            public <E extends MatchbookSDKException> void onError(E exception) {
                fail();
            }
        });
        boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    @Test
    public void successfulGetBalanceTest() throws InterruptedException {
        stubFor(get(urlPathEqualTo("/edge/rest/account/balance"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/getAccountBalanceSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        BalanceRequest balanceRequest = new BalanceRequest.Builder().build();
        userRestClient.getBalance(balanceRequest, new StreamObserver<Balance>() {
            @Override
            public void onNext(Balance balance) {
                assertThat(balance.getId()).isNotNull();
                assertThat(balance.getBalance()).isPositive();
                assertThat(balance.getCommissionReserve()).isPositive();
                assertThat(balance.getExposure()).isPositive();
                assertThat(balance.getFreeFunds()).isPositive();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }

            @Override
            public <E extends MatchbookSDKException> void onError(E exception) {
                fail();
            }
        });

        boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }
}
