package com.matchbook.sdk.stream.schedulers;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.UserClient;
import com.matchbook.sdk.rest.dtos.user.Balance;
import com.matchbook.sdk.rest.dtos.user.BalanceRequest;
import com.matchbook.sdk.stream.disruptor.messages.UserMessage;
import com.matchbook.sdk.stream.disruptor.publisher.UserPublisher;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;

public class BalanceScheduler implements Runnable {

    private UserClient userRestClient;
    private UserPublisher userPublisher;

    public BalanceScheduler(UserClient userRestClient,
            UserPublisher userPublisher) {
        this.userRestClient = userRestClient;
        this.userPublisher = userPublisher;
    }

    @Override
    public void run() {

        userRestClient.getBalance(new BalanceRequest.Builder().build(), new StreamObserver<Balance>() {
            @Override
            public void onNext(Balance balance) {
                UserMessage userMessage = new UserMessage();
                userMessage.setBalanceDTO(balance);
                userPublisher.publish(userMessage);
            }

            @Override
            public void onCompleted() {
                // do nothing
            }

            @Override
            public <E extends MatchbookSDKException> void onError(E exception) {
                //TODO Add retry policy
            }
        });
    }
}
