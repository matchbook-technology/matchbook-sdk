package com.matchbook.sdk.core.disruptor;

public interface DisruptorPublisher<T extends DisruptorMessage> {

    void publish(T message);

}
