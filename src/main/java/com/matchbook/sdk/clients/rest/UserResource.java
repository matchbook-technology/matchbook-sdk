package com.matchbook.sdk.clients.rest;

import com.matchbook.sdk.clients.rest.dtos.LoginRequest;
import com.matchbook.sdk.clients.rest.dtos.LoginResponse;
import com.matchbook.sdk.clients.rest.dtos.LogoutResponse;

public interface UserResource {

    LoginResponse login(LoginRequest loginRequest);

    LogoutResponse logout(String sessionToken);
}
