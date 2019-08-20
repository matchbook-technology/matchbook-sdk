package com.matchbook.sdk.stream.schedulers;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.UserClient;
import com.matchbook.sdk.rest.dtos.user.Login;

public class AuthenticationScheduler implements Runnable {

    private final UserClient userRestClient;

    public AuthenticationScheduler(UserClient userRestClient) {
        this.userRestClient = userRestClient;
    }

    @Override
    public void run() {
        userRestClient.login(new StreamObserver<Login>() {
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
