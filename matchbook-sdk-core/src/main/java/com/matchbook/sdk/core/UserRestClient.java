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

public interface UserRestClient extends RestClient {

    void login(LoginRequest loginRequest, StreamObserver<Login> response);

    void logout(LogoutRequest logoutRequest, StreamObserver<Logout> response);

    void getAccount(AccountRequest accountRequest, StreamObserver<Account> response);

    void getBalance(BalanceRequest balanceRequest, StreamObserver<Balance> response);

}
