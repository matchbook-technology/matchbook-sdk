package com.matchbook.sdk.stream.disruptor.messages;

public abstract class AbstractDisruptorMessage implements DisruptorMessage {

    private boolean isSkipped;
    private long sequence;

    protected AbstractDisruptorMessage() {
        super();
        isSkipped = false;
    }

    @Override
    public void setSkipped(boolean isSkipped) {
        this.isSkipped = isSkipped;
    }

    @Override
    public boolean isSkipped() {
        return isSkipped;
    }

    @Override
    public void setId(long sequence) {
        this.sequence = sequence;
    }

    @Override
    public long getId() {
        return sequence;
    }

}
