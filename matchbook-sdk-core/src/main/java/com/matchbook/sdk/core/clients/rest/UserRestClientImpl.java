package com.matchbook.sdk.core.clients.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginResponse;
import com.matchbook.sdk.core.clients.rest.dtos.user.Logout;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHTTPException;

public class UserRestClientImpl extends AbstractRestClient implements UserRestClient {

    private final ObjectWriter loginRequestWriter;
    private final ObjectReader loginResponseReader;

    public UserRestClientImpl(ClientConnectionManager clientConnectionManager) {
        super(clientConnectionManager);

        this.loginRequestWriter = clientConnectionManager.getMapper().writerFor(LoginRequest.class);
        this.loginResponseReader = clientConnectionManager.getMapper().readerFor(LoginResponse.class);
    }

    @Override
    public void login(LoginRequest loginRequest, StreamObserver<Login> loginObserver) {
        try {
            String path = clientConnectionManager.getClientConfig().getLoginUrl();
            String requestBody = loginRequestWriter.writeValueAsString(loginRequest);
            postRequest(path, requestBody, loginObserver, loginResponseReader);
        } catch (JsonProcessingException e) {
            loginObserver.onError(new MatchbookSDKHTTPException((e.getCause())));
        }
    }

    @Override
    public void logout(String sessionToken, StreamObserver<Logout> response) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
