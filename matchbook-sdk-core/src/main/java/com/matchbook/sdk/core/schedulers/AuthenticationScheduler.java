package com.matchbook.sdk.core.schedulers;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.UserRestClient;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.disruptor.CoordinatorMessage;
import com.matchbook.sdk.core.disruptor.CoordinatorPublisher;
import com.matchbook.sdk.core.disruptor.MessageType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;

public class AuthenticationScheduler implements Runnable {

    private final CoordinatorPublisher coordinatorPublisher;
    private final UserRestClient userRestClient;
    private final LoginRequest loginRequest;

    public AuthenticationScheduler(CoordinatorPublisher coordinatorPublisher,
            UserRestClient userRestClient, LoginRequest loginRequest) {
        this.coordinatorPublisher = coordinatorPublisher;
        this.userRestClient = userRestClient;
        this.loginRequest = loginRequest;
    }

    @Override
    public void run() {
        userRestClient.login(loginRequest, new StreamObserver<Login>() {
            @Override
            public void onNext(Login login) {
                CoordinatorMessage coordinatorMessage = new CoordinatorMessage();
                coordinatorMessage.setMessageType(MessageType.AUTH);
                coordinatorMessage.setLoginDTO(login);

                coordinatorPublisher.publish(coordinatorMessage);
            }

            @Override
            public void onCompleted() {
                //TODO: we should wait until successful login
            }

            @Override
            public <E extends MatchbookSDKException> void onError(E exception) {
                //TODO Add retry policy, but for now we can raise an exception
            }
        });
    }
}
