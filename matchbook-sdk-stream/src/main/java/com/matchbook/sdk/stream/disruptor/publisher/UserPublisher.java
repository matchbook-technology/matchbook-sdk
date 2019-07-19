package com.matchbook.sdk.stream.disruptor.publisher;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.matchbook.sdk.stream.disruptor.DisruptorPublisher;
import com.matchbook.sdk.stream.disruptor.eventtranslator.AccountEventTranslator;
import com.matchbook.sdk.stream.disruptor.messages.UserMessage;

public class UserPublisher implements DisruptorPublisher<UserMessage> {

    private final RingBuffer<UserMessage> ringBuffer;

    public UserPublisher(Disruptor<UserMessage> disruptor) {
        this.ringBuffer = disruptor.getRingBuffer();
    }

    @Override
    public void publish(UserMessage newMessage) {
        ringBuffer.publishEvent(new AccountEventTranslator(newMessage));
    }
}
