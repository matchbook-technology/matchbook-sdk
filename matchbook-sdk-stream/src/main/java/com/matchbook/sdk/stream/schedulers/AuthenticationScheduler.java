package com.matchbook.sdk.stream.schedulers;

import com.matchbook.sdk.common.StreamObserver;
import com.matchbook.sdk.core.UserRestClient;
import com.matchbook.sdk.core.dtos.user.Login;
import com.matchbook.sdk.core.dtos.user.LoginRequest;
import com.matchbook.sdk.common.exceptions.MatchbookSDKException;

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
