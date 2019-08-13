package com.matchbook.sdk.rest.clients.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.MatchbookSDKClientTest;
import com.matchbook.sdk.rest.UserClient;
import com.matchbook.sdk.rest.UserClientRest;
import com.matchbook.sdk.rest.dtos.user.Account;
import com.matchbook.sdk.rest.dtos.user.Balance;
import com.matchbook.sdk.rest.dtos.user.Login;
import com.matchbook.sdk.rest.dtos.user.Logout;
import org.junit.Test;

public class UserClientRestTest extends MatchbookSDKClientTest {

    private final UserClient userRestClient;

    public UserClientRestTest() {
        this.userRestClient = new UserClientRest(connectionManager);
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

        userRestClient.login(new StreamObserver<Login>() {
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
        userRestClient.login(new StreamObserver<Login>() {
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
    public void successfulLogoutTest() throws InterruptedException {
        stubFor(delete(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/logoutSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        userRestClient.logout(new StreamObserver<Logout>() {
            @Override
            public void onNext(Logout logout) {
                assertThat(logout.getSessionToken()).isNotEmpty();
                assertThat(logout.getUserId()).isNotZero();
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

        userRestClient.login(new StreamObserver<Login>() {
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

        userRestClient.getAccount(new StreamObserver<Account>() {
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

        userRestClient.getBalance(new StreamObserver<Balance>() {
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

    @Test
    public void successfulLoginAndGetBalanceTest() throws InterruptedException {
        // Perform first a login request with response that sets the session-token cookie
        // We expect that the following GET balance includes the same cookie in the request

        stubFor(post(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Set-Cookie", "session-token=2574_d4dcd1c54caacb4755a")
                        .withBodyFile("matchbook/loginSuccessfulResponse.json")));

        stubFor(get(urlPathEqualTo("/edge/rest/account/balance"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/getAccountBalanceSuccessfulResponse.json")));

        final CountDownLatch loginCountDownLatch = new CountDownLatch(1);
        userRestClient.login(buildStreamObserverWithCountdownLatch(loginCountDownLatch));

        boolean await = loginCountDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        final CountDownLatch balanceCountDownLatch = new CountDownLatch(1);
        userRestClient.getBalance(buildStreamObserverWithCountdownLatch(balanceCountDownLatch));

        await = balanceCountDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        verify(getRequestedFor(urlEqualTo("/edge/rest/account/balance"))
                .withCookie("session-token", equalTo("2574_d4dcd1c54caacb4755a")));
    }

    @Test
    public void successfulLoginAndLogoutTest() throws InterruptedException {
        // Perform a login request with response that sets the session-token cookie
        // then a logout request that should expire the cookie.
        // We expect that the following GET balance doesn't include the session-token cookie

        stubFor(post(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Set-Cookie", "session-token=2574_d4dcd1c54caacb4755a")
                        .withBodyFile("matchbook/loginSuccessfulResponse.json")));

        stubFor(delete(urlEqualTo("/bpapi/rest/security/session"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Set-Cookie", "session-token=2574_d4dcd1c54caacb4755a; Max-Age=0")
                        .withBodyFile("matchbook/logoutSuccessfulResponse.json")));

        stubFor(get(urlPathEqualTo("/edge/rest/account/balance"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/getAccountBalanceSuccessfulResponse.json")));

        final CountDownLatch loginCountDownLatch = new CountDownLatch(1);
        userRestClient.login(buildStreamObserverWithCountdownLatch(loginCountDownLatch));

        boolean await = loginCountDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        final CountDownLatch logoutCountDownLatch = new CountDownLatch(1);
        userRestClient.logout(buildStreamObserverWithCountdownLatch(logoutCountDownLatch));

        await = logoutCountDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        final CountDownLatch balanceCountDownLatch = new CountDownLatch(1);
        userRestClient.getBalance(buildStreamObserverWithCountdownLatch(balanceCountDownLatch));

        await = balanceCountDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        verify(getRequestedFor(urlEqualTo("/edge/rest/account/balance"))
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
