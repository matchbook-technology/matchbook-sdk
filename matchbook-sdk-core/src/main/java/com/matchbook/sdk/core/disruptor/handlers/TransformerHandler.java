package com.matchbook.sdk.core.disruptor.handlers;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;
import com.matchbook.sdk.core.disruptor.CoordinatorMessage;
import com.matchbook.sdk.core.model.mappers.auth.LoginToUserMapper;

public class TransformerHandler<T extends CoordinatorMessage> implements EventHandler<T>, LifecycleAware {

    private String oldThreadName;

    @Override
    public void onEvent(T event, long sequence, boolean endOfBatch) throws Exception {
        switch (event.getMessageType()) {
            case AUTH:
                event.setUser(LoginToUserMapper.mapLoginDTOToUser(event.getLoginDTO()));
                break;
            default:
        }
    }

    @Override
    public void onStart() {
        final Thread currentThread = Thread.currentThread();
        oldThreadName = currentThread.getName();
        currentThread.setName(TransformerHandler.class.getSimpleName());
    }

    @Override
    public void onShutdown() {
        Thread.currentThread().setName(oldThreadName);
    }
}
