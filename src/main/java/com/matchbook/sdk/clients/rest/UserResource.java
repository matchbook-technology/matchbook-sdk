package com.matchbook.sdk.clients.rest;

import com.matchbook.sdk.StreamObserver;
import com.matchbook.sdk.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.clients.rest.dtos.user.LoginResponse;
import com.matchbook.sdk.clients.rest.dtos.user.LogoutResponse;

public interface UserResource {

    void login(LoginRequest loginRequest, StreamObserver<LoginResponse> response);

    void logout(String sessionToken, StreamObserver<LogoutResponse> response);

}
