package com.matchbook.sdk.rest.clients.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.MatchbookSDKClientRest_IT;
import com.matchbook.sdk.rest.UserClientRest;
import com.matchbook.sdk.rest.dtos.user.Account;
import com.matchbook.sdk.rest.dtos.user.Balance;
import com.matchbook.sdk.rest.dtos.user.Login;
import com.matchbook.sdk.rest.dtos.user.Logout;
import org.junit.Before;
import org.junit.Test;

public class UserClientRest_IT extends MatchbookSDKClientRest_IT {

    private UserClientRest userClientRest;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        this.userClientRest = new UserClientRest(connectionManager);
    }

    @Test
    public void loginTest() throws InterruptedException {
        wireMockServer.stubFor(post(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/loginSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        userClientRest.login(new StreamObserver<Login>() {
            @Override
            public void onNext(Login login) {
                assertNotNull(login);
                assertNotNull(login.getUserId());
                assertThat(login.getSessionToken()).isNotEmpty();
                verifyAccount(login.getAccount());
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
    public void loginUsingIncorrectPasswordTest() throws InterruptedException {
        wireMockServer.stubFor(post(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(401)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/loginFailedResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        userClientRest.login(new StreamObserver<Login>() {
            @Override
            public void onNext(Login login) {
                fail();
            }

            @Override
            public void onError(MatchbookSDKException exception) {
                assertEquals(ErrorType.UNAUTHENTICATED, exception.getErrorType());
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                fail();
            }
        });

        boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    @Test
    public void loginEmptyResponseBodyTest() throws InterruptedException {
        wireMockServer.stubFor(post(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("")));

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        userClientRest.login(new StreamObserver<Login>() {
            @Override
            public void onNext(Login login) {
                fail();
            }

            @Override
            public void onError(MatchbookSDKException exception) {
                assertEquals(ErrorType.HTTP, exception.getErrorType());
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                fail();
            }
        });

        boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    @Test
    public void logoutTest() throws InterruptedException {
        wireMockServer.stubFor(delete(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/logoutSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        userClientRest.logout(new StreamObserver<Logout>() {
            @Override
            public void onNext(Logout logout) {
                assertNotNull(logout);
                assertNotNull(logout.getUserId());
                assertThat(logout.getSessionToken()).isNotEmpty();
                assertThat(logout.getUsername()).isNotEmpty();
                countDownLatch.countDown();
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

        boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    @Test
    public void getAccountTest() throws InterruptedException {
        wireMockServer.stubFor(get(urlPathEqualTo("/edge/rest/account"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/getAccountSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        userClientRest.getAccount(new StreamObserver<Account>() {
            @Override
            public void onNext(Account account) {
                verifyAccount(account);
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
        boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    private void verifyAccount(Account account) {
        assertNotNull(account);
        assertNotNull(account.getId());
        assertThat(account.getUsername()).isNotEmpty();
        assertNotNull(account.getCurrency());
        assertNotNull(account.getOddsType());
        assertNotNull(account.getBalance());
        assertThat(account.getBalance()).isNotNegative();
    }

    @Test
    public void getBalanceTest() throws InterruptedException {
        wireMockServer.stubFor(get(urlPathEqualTo("/edge/rest/account/balance"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/getAccountBalanceSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        userClientRest.getBalance(new StreamObserver<Balance>() {
            @Override
            public void onNext(Balance balance) {
                assertNotNull(balance);
                assertNotNull(balance.getId());
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

        boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

    @Test
    public void loginAndGetBalanceTest() throws InterruptedException {
        // Perform first a login request with response that sets the session-token cookie
        // We expect that the following GET balance includes the same cookie in the request

        wireMockServer.stubFor(post(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Set-Cookie", "session-token=2574_d4dcd1c54caacb4755a")
                        .withBodyFile("matchbook/user/loginSuccessfulResponse.json")));

        wireMockServer.stubFor(get(urlPathEqualTo("/edge/rest/account/balance"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/getAccountBalanceSuccessfulResponse.json")));

        final CountDownLatch loginCountDownLatch = new CountDownLatch(1);
        userClientRest.login(buildStreamObserverWithCountdownLatch(loginCountDownLatch));

        boolean await = loginCountDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        final CountDownLatch balanceCountDownLatch = new CountDownLatch(1);
        userClientRest.getBalance(buildStreamObserverWithCountdownLatch(balanceCountDownLatch));

        await = balanceCountDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        wireMockServer.verify(getRequestedFor(urlEqualTo("/edge/rest/account/balance"))
                .withCookie("session-token", equalTo("2574_d4dcd1c54caacb4755a")));
    }

    @Test
    public void loginAndLogoutTest() throws InterruptedException {
        // Perform a login request with response that sets the session-token cookie
        // then a logout request that should expire the cookie.
        // We expect that the following GET balance doesn't include the session-token cookie

        wireMockServer.stubFor(post(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Set-Cookie", "session-token=2574_d4dcd1c54caacb4755a")
                        .withBodyFile("matchbook/user/loginSuccessfulResponse.json")));

        wireMockServer.stubFor(delete(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Set-Cookie", "session-token=2574_d4dcd1c54caacb4755a; Max-Age=0")
                        .withBodyFile("matchbook/user/logoutSuccessfulResponse.json")));

        wireMockServer.stubFor(get(urlPathEqualTo("/edge/rest/account/balance"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/getAccountBalanceSuccessfulResponse.json")));

        final CountDownLatch loginCountDownLatch = new CountDownLatch(1);
        userClientRest.login(buildStreamObserverWithCountdownLatch(loginCountDownLatch));

        boolean await = loginCountDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        final CountDownLatch logoutCountDownLatch = new CountDownLatch(1);
        userClientRest.logout(buildStreamObserverWithCountdownLatch(logoutCountDownLatch));

        await = logoutCountDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        final CountDownLatch balanceCountDownLatch = new CountDownLatch(1);
        userClientRest.getBalance(buildStreamObserverWithCountdownLatch(balanceCountDownLatch));

        await = balanceCountDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        wireMockServer.verify(getRequestedFor(urlEqualTo("/edge/rest/account/balance"))
                .withoutHeader("set-cookie"));
    }

    private <T> StreamObserver<T> buildStreamObserverWithCountdownLatch(CountDownLatch countDownLatch) {
        return new StreamObserver<T>() {
            @Override
            public void onNext(T response) {
                // do nothing
            }

            @Override
            public void onError(MatchbookSDKException e) {
                fail();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        };
    }

}
