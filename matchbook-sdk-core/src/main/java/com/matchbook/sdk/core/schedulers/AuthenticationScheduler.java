package com.matchbook.sdk.core.schedulers;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.UserRestClient;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;

public class AuthenticationScheduler implements Runnable {

    private final UserRestClient userRestClient;
    private final LoginRequest loginRequest;

    public AuthenticationScheduler(UserRestClient userRestClient, LoginRequest loginRequest) {
        this.userRestClient = userRestClient;
        this.loginRequest = loginRequest;
    }

    @Override
    public void run() {
        userRestClient.login(loginRequest, new StreamObserver<Login>() {
            @Override
            public void onNext(Login login) {
                //do nothing
            }

            @Override
            public void onCompleted() {
                //do nothing
            }

            @Override
            public <E extends MatchbookSDKException> void onError(E exception) {
                //TODO Add retry policy
            }
        });
    }
}
