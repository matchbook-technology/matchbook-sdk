package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.dtos.user.Account;
import com.matchbook.sdk.rest.dtos.user.Balance;
import com.matchbook.sdk.rest.dtos.user.Login;
import com.matchbook.sdk.rest.dtos.user.Logout;

public interface UserClient extends Client {

    void login(StreamObserver<Login> response);

    void logout(StreamObserver<Logout> response);

    void getAccount(StreamObserver<Account> response);

    void getBalance(StreamObserver<Balance> response);

}
