package com.matchbook.sdk.stream.disruptor;

import com.matchbook.sdk.stream.disruptor.messages.DisruptorMessage;

public interface DisruptorPublisher<T extends DisruptorMessage> {

    void publish(T message);

}
