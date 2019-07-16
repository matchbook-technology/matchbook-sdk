package com.matchbook.sdk.core.clients.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.user.Account;
import com.matchbook.sdk.core.clients.rest.dtos.user.AccountRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.Balance;
import com.matchbook.sdk.core.clients.rest.dtos.user.BalanceRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.Logout;

public interface UserRestClient extends RestClient {

    void login(LoginRequest loginRequest, StreamObserver<Login> response);

    void logout(String sessionToken, StreamObserver<Logout> response);

    void getAccount(AccountRequest accountRequest, StreamObserver<Account> response);

    void getBalance(BalanceRequest balanceRequest, StreamObserver<Balance> response);

}
