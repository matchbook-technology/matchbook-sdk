package com.matchbook.sdk.core.disruptor.handlers;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;
import com.matchbook.sdk.core.disruptor.CoordinatorMessage;

public class AggregatorHandler<T extends CoordinatorMessage> implements EventHandler<T>, LifecycleAware {

    private String oldThreadName;

    @Override
    public void onEvent(T event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("AggregatorHandler: " + event + " sequence:" + sequence);
    }

    @Override
    public void onStart() {
        final Thread currentThread = Thread.currentThread();
        oldThreadName = currentThread.getName();
        currentThread.setName(AggregatorHandler.class.getSimpleName());
    }

    @Override
    public void onShutdown() {
        Thread.currentThread().setName(oldThreadName);
    }
}
