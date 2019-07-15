package com.matchbook.sdk.core.disruptor;

import com.lmax.disruptor.dsl.Disruptor;
import com.matchbook.sdk.core.disruptor.handlers.UserPublisherHandler;
import com.matchbook.sdk.core.disruptor.handlers.UserTransformerHandler;
import com.matchbook.sdk.core.disruptor.messages.UserMessage;

public class UserDisruptorPipeliner implements AutoCloseable {

    private Disruptor<UserMessage> disruptor;
    private UserTransformerHandler<UserMessage> userTransformerHandler;
    private UserPublisherHandler<UserMessage> userPublisherHandler;

    public UserDisruptorPipeliner(Disruptor<UserMessage> disruptor) {
        this.disruptor = disruptor;

        this.userTransformerHandler = new UserTransformerHandler<>();
        this.userPublisherHandler = new UserPublisherHandler<>();

        initDisruptor();
    }

    private void initDisruptor() {
        disruptor.handleEventsWith(userTransformerHandler)
                .then(userPublisherHandler);
        disruptor.start();
    }

    public UserTransformerHandler<UserMessage> getUserTransformerHandler() {
        return userTransformerHandler;
    }

    public UserPublisherHandler<UserMessage> getUserPublisherHandler() {
        return userPublisherHandler;
    }

    @Override
    public void close() {
        disruptor.shutdown();
    }

}
