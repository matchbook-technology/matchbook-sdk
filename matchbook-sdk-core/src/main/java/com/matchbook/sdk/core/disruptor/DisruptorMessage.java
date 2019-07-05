package com.matchbook.sdk.core.disruptor;

public interface DisruptorMessage {

    String getId();

    void setSkipped(boolean skipped);

    boolean isSkipped();

    void setSequence(long sequence);

    long getSequence();

    void setReceivedTime(long receivedTime);

    long getReceivedTime();

    void reset();

}
