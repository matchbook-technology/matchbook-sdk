package com.matchbook.sdk.core.clients.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.Logout;

public interface UserRestClient extends RestClient {

    void login(LoginRequest loginRequest, StreamObserver<Login> response);

    void logout(String sessionToken, StreamObserver<Logout> response);

}
