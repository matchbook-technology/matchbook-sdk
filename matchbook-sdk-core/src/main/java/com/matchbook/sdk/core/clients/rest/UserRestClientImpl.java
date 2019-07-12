package com.matchbook.sdk.core.clients.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.user.Account;
import com.matchbook.sdk.core.clients.rest.dtos.user.AccountRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.AccountResponse;
import com.matchbook.sdk.core.clients.rest.dtos.user.Balance;
import com.matchbook.sdk.core.clients.rest.dtos.user.BalanceRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.BalanceResponse;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.Logout;
import com.matchbook.sdk.core.configs.ConnectionManager;

public class UserRestClientImpl extends AbstractRestClient implements UserRestClient {

    public UserRestClientImpl(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void login(LoginRequest loginRequest, StreamObserver<Login> loginObserver) {
        String url = buildLoginUrl();
        postRequest(url, loginRequest, loginObserver, Login.class);
    }

    @Override
    public void logout(String sessionToken, StreamObserver<Logout> response) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void getAccount(AccountRequest accountRequest, StreamObserver<Account> response) {
        String url = buildSportsUrl(accountRequest.resourcePath());
        getRequest(url, accountRequest, response, AccountResponse.class);
    }

    @Override
    public void getBalance(BalanceRequest balanceRequest, StreamObserver<Balance> response) {
        String url = buildSportsUrl(balanceRequest.resourcePath());
        getRequest(url, balanceRequest, response, BalanceResponse.class);
    }
}
