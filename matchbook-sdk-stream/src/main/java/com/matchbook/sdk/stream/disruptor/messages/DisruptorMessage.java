package com.matchbook.sdk.stream.disruptor.messages;

import java.io.Serializable;

public interface DisruptorMessage extends Serializable {

    void setSkipped(boolean skipped);

    boolean isSkipped();

    void setId(long sequence);

    long getId();

}
