package com.matchbook.sdk.core.disruptor;

import com.matchbook.sdk.core.disruptor.messages.DisruptorMessage;

public interface DisruptorPublisher<T extends DisruptorMessage> {

    void publish(T message);

}
