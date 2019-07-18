package com.matchbook.sdk.stream.disruptor;

import com.matchbook.sdk.stream.disruptor.messages.DisruptorMessage;

public interface DisruptorHandler<T extends DisruptorMessage> {

    void handleMessage(T message, long sequence);

}
