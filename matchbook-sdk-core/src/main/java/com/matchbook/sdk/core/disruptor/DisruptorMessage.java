package com.matchbook.sdk.core.disruptor;

public interface DisruptorMessage {

    void setSkipped(boolean skipped);

    boolean isSkipped();

    void setId(long sequence);

    long getId();

    void setReceivedTime(long receivedTime);

    long getReceivedTime();

    void reset();

}
