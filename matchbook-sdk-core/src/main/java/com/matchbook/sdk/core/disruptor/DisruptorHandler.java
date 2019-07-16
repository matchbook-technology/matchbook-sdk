package com.matchbook.sdk.core.disruptor;

import com.matchbook.sdk.core.disruptor.messages.DisruptorMessage;

public interface DisruptorHandler<T extends DisruptorMessage> {

    void handleMessage(T message, long sequence);

}
