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

public interface UserClient extends Client {

    void login(LoginRequest loginRequest, StreamObserver<Login> response);

    void logout(LogoutRequest logoutRequest, StreamObserver<Logout> response);

    void getAccount(AccountRequest accountRequest, StreamObserver<Account> response);

    void getBalance(BalanceRequest balanceRequest, StreamObserver<Balance> response);

}
