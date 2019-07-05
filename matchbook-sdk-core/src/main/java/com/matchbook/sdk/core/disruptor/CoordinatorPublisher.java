package com.matchbook.sdk.core.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class CoordinatorPublisher implements DisruptorPublisher<CoordinatorMessage> {

    private final RingBuffer<CoordinatorMessage> ringBuffer;

    public CoordinatorPublisher(Disruptor<CoordinatorMessage> disruptor) {
        this.ringBuffer = disruptor.getRingBuffer();
    }

    @Override
    public void publish(CoordinatorMessage newMessage) {
        long sequence = ringBuffer.next();
        try {
            CoordinatorMessage availableMessage = ringBuffer.get(sequence);
            availableMessage.reset();

            availableMessage.setMessageType(newMessage.getMessageType());
            availableMessage.setReceivedTime(System.nanoTime());
            availableMessage.setSequence(sequence);

            availableMessage.setLoginDTO(newMessage.getLoginDTO());
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
