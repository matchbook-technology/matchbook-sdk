package com.matchbook.sdk.core.clients.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginResponse;
import com.matchbook.sdk.core.clients.rest.dtos.user.LogoutResponse;

public interface UserRestClient extends MatchbookRestClient {

    void login(LoginRequest loginRequest, StreamObserver<LoginResponse> response);

    void logout(String sessionToken, StreamObserver<LogoutResponse> response);

}
