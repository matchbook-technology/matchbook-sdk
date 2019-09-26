package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.dtos.user.Account;
import com.matchbook.sdk.rest.dtos.user.AccountRequest;
import com.matchbook.sdk.rest.dtos.user.Balance;
import com.matchbook.sdk.rest.dtos.user.BalanceRequest;
import com.matchbook.sdk.rest.dtos.user.Login;
import com.matchbook.sdk.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.rest.dtos.user.Logout;
import com.matchbook.sdk.rest.dtos.user.LogoutRequest;
import com.matchbook.sdk.rest.readers.user.AccountReader;
import com.matchbook.sdk.rest.readers.user.BalanceReader;
import com.matchbook.sdk.rest.readers.user.LoginReader;
import com.matchbook.sdk.rest.readers.user.LogoutReader;

public class UserClientRest extends AbstractRestClient implements UserClient {

    public UserClientRest(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void login(StreamObserver<Login> loginObserver) {
        LoginRequest loginRequest = new LoginRequest.Builder(
                    connectionManager.getClientConfig().getUsername(),
                    connectionManager.getClientConfig().getPassword())
                .build();
        String url = buildLoginUrl();
        postRequest(url, loginRequest, loginObserver, new LoginReader());
    }

    @Override
    public void logout(StreamObserver<Logout> logoutObserver) {
        LogoutRequest logoutRequest = new LogoutRequest.Builder().build();
        String url = buildLoginUrl();
        deleteRequest(url, logoutRequest, logoutObserver, new LogoutReader());
    }

    @Override
    public void getAccount(StreamObserver<Account> accountObserver) {
        AccountRequest accountRequest = new AccountRequest.Builder().build();
        String url = buildSportsUrl(accountRequest.resourcePath());
        getRequest(url, accountRequest, accountObserver, new AccountReader());
    }

    @Override
    public void getBalance(StreamObserver<Balance> balanceObserver) {
        BalanceRequest balanceRequest = new BalanceRequest.Builder().build();
        String url = buildSportsUrl(balanceRequest.resourcePath());
        getRequest(url, balanceRequest, balanceObserver, new BalanceReader());
    }

}
