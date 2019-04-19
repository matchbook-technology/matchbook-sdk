package com.matchbook.sdk.core.services;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.UserRestClient;
import com.matchbook.sdk.core.clients.rest.UserRestClientImpl;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.matchbook.sdk.core.configs.SessionManager;
import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.model.dataobjects.LoginEnvelope;
import com.matchbook.sdk.core.model.dataobjects.auth.Credentials;
import com.matchbook.sdk.core.model.dataobjects.auth.User;
import com.matchbook.sdk.core.model.mappers.auth.LoginToUserMapper;

public class AuthRestService implements AuthService, SessionManager {

    private final UserRestClient userRestClient;
    private final LoginToUserMapper mapper;

    private String sessionToken;

    public AuthRestService(ClientConnectionManager clientConnectionManager) {
        this.userRestClient = new UserRestClientImpl(clientConnectionManager);
        this.mapper = new LoginToUserMapper();
    }

    @Override
    public void login(Credentials credentials, StreamObserver<LoginEnvelope> streamObserver) {
        LoginRequest loginRequest = new LoginRequest.Builder(credentials.getUsername(),
                credentials.getPassword()).build();

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

    @Override
    public String sessionToken(char[] username, char[] password) {
        if (Objects.nonNull(sessionToken)) {
            return sessionToken;
        }

        sessionToken = newSessionToken(username, password);
        return sessionToken;
    }

    private String newSessionToken(char[] username, char[] password) throws MatchbookSDKException {
        final CompletableFuture<String> future = new CompletableFuture<>();
        Credentials credentials = new Credentials.Builder(username, password).build();
        login(credentials, new StreamObserver<LoginEnvelope>() {

            @Override
            public void onNext(LoginEnvelope loginEnvelope) {
                String sessionToken = loginEnvelope.getUser().getSessionToken();
                future.complete(sessionToken);
            }

            @Override
            public void onCompleted() {
                // do nothing
            }

            @Override
            public <E extends MatchbookSDKException> void onError(E exception) {
                future.completeExceptionally(exception);
            }
        });

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new MatchbookSDKException(e, ErrorType.UNAUTHENTICATED);
        }
    }

    @Override
    public void invalidateSessionToken() {
        sessionToken = null;
    }

}
