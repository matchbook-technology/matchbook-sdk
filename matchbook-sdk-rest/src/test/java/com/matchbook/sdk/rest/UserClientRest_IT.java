package com.matchbook.sdk.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.rest.dtos.user.Account;
import com.matchbook.sdk.rest.dtos.user.Balance;
import com.matchbook.sdk.rest.dtos.user.Login;
import com.matchbook.sdk.rest.dtos.user.Logout;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("integration")
public class UserClientRest_IT extends MatchbookSDKClientRest_IT<UserClientRest> {

    @Override
    protected UserClientRest newClientRest(ConnectionManager connectionManager) {
        return new UserClientRest(connectionManager);
    }

    @Test
    @DisplayName("Login successfully")
    void loginTest() {
        String url = "/bpapi/rest/security/session";
        wireMockServer.stubFor(post(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/loginSuccessfulResponse.json")));

        ResponseStreamObserver<Login> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyLogin);
        clientRest.login(streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyLogin(Login login) {
        assertThat(login).isNotNull();
        assertThat(login.getUserId()).isNotNull();
        assertThat(login.getSessionToken()).isNotEmpty();
        verifyAccount(login.getAccount());
    }

    @Test
    @DisplayName("Login with incorrect password")
    void loginIncorrectPasswordTest() {
        String url = "/bpapi/rest/security/session";
        wireMockServer.stubFor(post(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(401)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/loginFailedResponse.json")));

        ResponseStreamObserver<Login> streamObserver = new FailedResponseStreamObserver<>(exception -> {
            assertThat(exception).isNotNull();
            assertThat(exception.getErrorType()).isEqualTo(ErrorType.UNAUTHENTICATED);
        });
        clientRest.login(streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    @DisplayName("Logout successfully")
    void logoutTest() {
        String url = "/bpapi/rest/security/session";
        wireMockServer.stubFor(delete(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/logoutSuccessfulResponse.json")));

        ResponseStreamObserver<Logout> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyLogout);
        clientRest.logout(streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(deleteRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyLogout(Logout logout) {
        assertThat(logout).isNotNull();
        assertThat(logout.getUserId()).isNotNull();
        assertThat(logout.getSessionToken()).isNotEmpty();
        assertThat(logout.getUsername()).isNotEmpty();
    }

    @Test
    @DisplayName("Get account details")
    void getAccountTest() {
        String url = "/edge/rest/account";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/getAccountSuccessfulResponse.json")));

        ResponseStreamObserver<Account> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyAccount);
        clientRest.getAccount(streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyAccount(Account account) {
        assertThat(account).isNotNull();
        assertThat(account.getId()).isNotNull();
        assertThat(account.getUsername()).isNotEmpty();
        assertThat(account.getCurrency()).isNotNull();
        assertThat(account.getOddsType()).isNotNull();
        assertThat(account.getBalance()).isNotNull();
        assertThat(account.getBalance()).isNotNegative();
    }

    @Test
    @DisplayName("Get balance")
    void getBalanceTest() {
        String url = "/edge/rest/account/balance";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/getAccountBalanceSuccessfulResponse.json")));

        ResponseStreamObserver<Balance> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyBalance);
        clientRest.getBalance(streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyBalance(Balance balance) {
        assertThat(balance).isNotNull();
        assertThat(balance.getId()).isNotNull();
        assertThat(balance.getBalance()).isNotNegative();
        assertThat(balance.getCommissionReserve()).isNotNegative();
        assertThat(balance.getExposure()).isNotNegative();
        assertThat(balance.getFreeFunds()).isNotNegative();
    }

    @Test
    @DisplayName("Set session token after login")
    void loginAndGetBalanceTest() {
        /*
         * Perform a login request getting a response that sets the session-token cookie.
         * We expect that the following GET balance request includes the same cookie.
         */
        String loginUrl = "/bpapi/rest/security/session";
        String sessionToken = "2574_d4dcd1c54caacb4755a";
        wireMockServer.stubFor(post(urlPathEqualTo(loginUrl))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Set-Cookie", "session-token=" + sessionToken)
                        .withBodyFile("matchbook/user/loginSuccessfulResponse.json")));

        String balanceUrl = "/edge/rest/account/balance";
        wireMockServer.stubFor(get(urlPathEqualTo(balanceUrl))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/getAccountBalanceSuccessfulResponse.json")));

        ResponseStreamObserver<Login> loginStreamObserver = new SuccessfulResponseStreamObserver<>(1);
        clientRest.login(loginStreamObserver);
        loginStreamObserver.waitTermination();

        ResponseStreamObserver<Balance> balanceStreamObserver = new SuccessfulResponseStreamObserver<>(1);
        clientRest.getBalance(balanceStreamObserver);
        balanceStreamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(balanceUrl))
                .withCookie("session-token", equalTo(sessionToken)));
        wireMockServer.verify(anyRequestedFor(anyUrl())
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    @DisplayName("Remove session token after logout")
    void loginAndLogoutTest() {
        /*
         * Firstly, perform a login request getting a response that sets the session-token cookie.
         * Then, a logout request that expires the cookie.
         * We expect that the following GET balance request doesn't include the session-token cookie.
         */
        String loginLogoutUrl = "/bpapi/rest/security/session";
        String sessionToken = "2574_d4dcd1c54caacb4755a";
        wireMockServer.stubFor(post(urlPathEqualTo(loginLogoutUrl))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Set-Cookie", "session-token=" + sessionToken)
                        .withBodyFile("matchbook/user/loginSuccessfulResponse.json")));

        wireMockServer.stubFor(delete(urlPathEqualTo(loginLogoutUrl))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Set-Cookie", "session-token=" + sessionToken + "; Max-Age=0")
                        .withBodyFile("matchbook/user/logoutSuccessfulResponse.json")));

        String balanceUrl = "/edge/rest/account/balance";
        wireMockServer.stubFor(get(urlPathEqualTo(balanceUrl))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/user/unauthorizedResponse.json")));

        ResponseStreamObserver<Login> loginStreamObserver = new SuccessfulResponseStreamObserver<>(1);
        clientRest.login(loginStreamObserver);
        loginStreamObserver.waitTermination();

        ResponseStreamObserver<Logout> logoutStreamObserver = new SuccessfulResponseStreamObserver<>(1);
        clientRest.logout(logoutStreamObserver);
        logoutStreamObserver.waitTermination();

        ResponseStreamObserver<Balance> balanceStreamObserver = new FailedResponseStreamObserver<>(exception -> {
            assertThat(exception).isNotNull();
            assertThat(exception.getErrorType()).isEqualTo(ErrorType.UNAUTHENTICATED);
        });
        clientRest.getBalance(balanceStreamObserver);
        balanceStreamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(balanceUrl))
                .withoutHeader("set-cookie"));
        wireMockServer.verify(anyRequestedFor(anyUrl())
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    @DisplayName("Unexpected server error in users API")
    void serverErrorTest() {
        String url = "/edge/rest/account/balance";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/serverErrorResponse.json")));

        ResponseStreamObserver<Balance> streamObserver = new FailedResponseStreamObserver<>(exception -> {
            assertThat(exception).isNotNull();
            assertThat(exception.getErrorType()).isEqualTo(ErrorType.HTTP);
        });
        clientRest.getBalance(streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    @DisplayName("Unexpected empty response in users API")
    void unexpectedEmptyResponseTest() {
        String url = "/bpapi/rest/security/session";
        wireMockServer.stubFor(post(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("")));

        ResponseStreamObserver<Login> streamObserver = new FailedResponseStreamObserver<>(exception -> {
            assertThat(exception).isNotNull();
            assertThat(exception.getErrorType()).isEqualTo(ErrorType.HTTP);
        });
        clientRest.login(streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

}
