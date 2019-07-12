package com.matchbook.sdk.core.disruptor.handlers;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;
import com.matchbook.sdk.core.disruptor.messages.UserMessage;
import com.matchbook.sdk.core.model.mappers.user.UserModelMapper;

public class UserTransformerHandler<T extends UserMessage> implements EventHandler<T>, LifecycleAware {

    private String oldThreadName;

    private UserModelMapper userModelMapper;

    public UserTransformerHandler() {
        userModelMapper = new UserModelMapper();
    }

    @Override
    public void onEvent(T event, long sequence, boolean endOfBatch) throws Exception {
        event.setBalance(userModelMapper.mapBalance(event.getBalanceDTO()));
    }

    @Override
    public void onStart() {
        final Thread currentThread = Thread.currentThread();
        oldThreadName = currentThread.getName();
        currentThread.setName(UserTransformerHandler.class.getSimpleName());
    }

    @Override
    public void onShutdown() {
        Thread.currentThread().setName(oldThreadName);
    }
}
