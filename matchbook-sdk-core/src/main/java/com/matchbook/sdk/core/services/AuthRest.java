package com.matchbook.sdk.core.services;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.UserRestClient;
import com.matchbook.sdk.core.clients.rest.UserRestClientImpl;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.model.dataobjects.LoginEnvelope;
import com.matchbook.sdk.core.model.dataobjects.auth.User;

public class AuthRest implements Auth {

    private final ClientConnectionManager clientConnectionManager;
    private final UserRestClient userRestClient;

    public AuthRest(ClientConnectionManager clientConnectionManager) {
        this.clientConnectionManager = clientConnectionManager;

        this.userRestClient = new UserRestClientImpl(clientConnectionManager);
    }

    @Override
    public void login(User user, StreamObserver<LoginEnvelope> streamObserver) {

        LoginRequest loginRequest = new LoginRequest.Builder(user.getUsername(), user.getPassword()).build();

        userRestClient.login(loginRequest, new StreamObserver<Login>() {
            @Override
            public void onNext(Login login) {
                //TODO: map entity and send to the observer 
            }

            @Override
            public void onCompleted() {
                streamObserver.onCompleted();
            }

            @Override
            public <E extends MatchbookSDKException> void onError(E exception) {
                streamObserver.onError(exception);
            }
        });
    }

}
