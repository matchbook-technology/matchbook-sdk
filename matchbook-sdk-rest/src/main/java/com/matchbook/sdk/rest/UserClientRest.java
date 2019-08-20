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

public class UserClientRest extends AbstractRestClient implements UserClient {

    public UserClientRest(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void login(StreamObserver<Login> loginObserver) {
        String url = buildLoginUrl();
        postRequest(url, new LoginRequest.Builder(connectionManager.getClientConfig().getUsername(),
                connectionManager.getClientConfig().getPassword()).build(), loginObserver, Login.class);
    }

    @Override
    public void logout(StreamObserver<Logout> response) {
        String url = buildLoginUrl();
        deleteRequest(url, new LogoutRequest.Builder().build(), response, Logout.class);
    }

    @Override
    public void getAccount(StreamObserver<Account> response) {
        AccountRequest accountRequest = new AccountRequest.Builder().build();
        String url = buildSportsUrl(accountRequest.resourcePath());
        getRequest(url, accountRequest, response, Account.class);
    }

    @Override
    public void getBalance(StreamObserver<Balance> response) {
        BalanceRequest balanceRequest = new BalanceRequest.Builder().build();
        String url = buildSportsUrl(balanceRequest.resourcePath());
        getRequest(url, balanceRequest, response, Balance.class);
    }
}
