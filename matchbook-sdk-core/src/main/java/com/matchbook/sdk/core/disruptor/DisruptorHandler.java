package com.matchbook.sdk.core.disruptor;

public interface DisruptorHandler<T extends DisruptorMessage> {

    void handleMessage(T message, long sequence);

}
