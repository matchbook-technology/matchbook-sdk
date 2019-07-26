package com.matchbook.sdk.stream.disruptor.handlers;

import java.util.Objects;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.stream.disruptor.messages.UserMessage;
import com.matchbook.sdk.stream.model.dataobjects.user.Balance;

public class UserPublisherHandler<T extends UserMessage> implements EventHandler<T>, LifecycleAware {

    private String originalThreadName;

    private StreamObserver<Balance> observer;

    public StreamObserver<Balance> getObserver() {
        return observer;
    }

    public void registerStreamConsumer(StreamObserver<Balance> observer) {
        this.observer = observer;
    }

    @Override
    public void onEvent(T event, long sequence, boolean endOfBatch) throws Exception {
        if (Objects.nonNull(observer)) {
            observer.onNext(event.getBalance());
        }
    }

    @Override
    public void onStart() {
        final Thread currentThread = Thread.currentThread();
        originalThreadName = currentThread.getName();
        currentThread.setName(UserPublisherHandler.class.getSimpleName());
    }

    @Override
    public void onShutdown() {
        Thread.currentThread().setName(originalThreadName);
    }
}
