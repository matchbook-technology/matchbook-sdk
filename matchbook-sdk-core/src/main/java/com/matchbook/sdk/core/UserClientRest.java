package com.matchbook.sdk.core;

import com.matchbook.sdk.common.StreamObserver;
import com.matchbook.sdk.core.dtos.user.Account;
import com.matchbook.sdk.core.dtos.user.AccountRequest;
import com.matchbook.sdk.core.dtos.user.Balance;
import com.matchbook.sdk.core.dtos.user.BalanceRequest;
import com.matchbook.sdk.core.dtos.user.Login;
import com.matchbook.sdk.core.dtos.user.LoginRequest;
import com.matchbook.sdk.core.dtos.user.Logout;
import com.matchbook.sdk.core.dtos.user.LogoutRequest;

public class UserClientRest extends AbstractRestClient implements UserClient {

    public UserClientRest(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void login(LoginRequest loginRequest, StreamObserver<Login> loginObserver) {
        String url = buildLoginUrl();
        postRequest(url, loginRequest, loginObserver, Login.class);
    }

    @Override
    public void logout(LogoutRequest logoutRequest, StreamObserver<Logout> response) {
        String url = buildLoginUrl();
        deleteRequest(url, logoutRequest, response, Logout.class);
    }

    @Override
    public void getAccount(AccountRequest accountRequest, StreamObserver<Account> response) {
        String url = buildSportsUrl(accountRequest.resourcePath());
        getRequest(url, accountRequest, response, Account.class);
    }

    @Override
    public void getBalance(BalanceRequest balanceRequest, StreamObserver<Balance> response) {
        String url = buildSportsUrl(balanceRequest.resourcePath());
        getRequest(url, balanceRequest, response, Balance.class);
    }
}
