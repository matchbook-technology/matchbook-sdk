package com.matchbook.sdk.core.clients.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.Logout;
import com.matchbook.sdk.core.configs.ConnectionManager;

public class UserRestClientImpl extends AbstractRestClient implements UserRestClient {

    public UserRestClientImpl(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void login(LoginRequest loginRequest, StreamObserver<Login> loginObserver) {
        String url = getClientConfig().getLoginUrl();
        postRequest(url, loginRequest, loginObserver, Login.class);
    }

    @Override
    public void logout(String sessionToken, StreamObserver<Logout> response) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
