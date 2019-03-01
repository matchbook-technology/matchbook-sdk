package com.matchbook.sdk.clients.rest;

import com.matchbook.sdk.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.clients.rest.dtos.user.LoginResponse;
import com.matchbook.sdk.clients.rest.dtos.user.LogoutResponse;

public interface UserResource {

    LoginResponse login(LoginRequest loginRequest);

    LogoutResponse logout(String sessionToken);

}
