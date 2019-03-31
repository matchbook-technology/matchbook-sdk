package com.matchbook.sdk.core.services;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.UserRestClient;
import com.matchbook.sdk.core.clients.rest.UserRestClientImpl;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.model.dataobjects.LoginEnvelope;
import com.matchbook.sdk.core.model.dataobjects.auth.Credentials;
import com.matchbook.sdk.core.model.dataobjects.auth.User;
import com.matchbook.sdk.core.model.mappers.auth.MBLoginToUserMapper;

public class AuthRest implements Auth {

    private final UserRestClient userRestClient;
    private final MBLoginToUserMapper mapper;

    public AuthRest(ClientConnectionManager clientConnectionManager) {

        this.userRestClient = new UserRestClientImpl(clientConnectionManager);
        this.mapper = new MBLoginToUserMapper();
    }

    @Override
    public void login(Credentials credentials, StreamObserver<LoginEnvelope> streamObserver) {

        LoginRequest loginRequest = new LoginRequest.Builder(credentials.getUsername(), credentials.getPassword()).build();

        userRestClient.login(loginRequest, new StreamObserver<Login>() {
            @Override
            public void onNext(Login login) {
                User user = mapper.mapToModel(login);
                LoginEnvelope loginEnvelope = new LoginEnvelope.Builder(user).build();

                streamObserver.onNext(loginEnvelope);
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
