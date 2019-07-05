package com.matchbook.sdk.core.disruptor;

public abstract class AbstractDisruptorMessage implements DisruptorMessage {

    protected MessageType messageType;
    private boolean isSkipped;
    private long receivedTime;
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
    public void setReceivedTime(long receivedTime) {
        this.receivedTime = receivedTime;
    }

    @Override
    public long getReceivedTime() {
        return receivedTime;
    }

    @Override
    public void setId(long sequence) {
        this.sequence = sequence;
    }

    @Override
    public long getId() {
        return sequence;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

}
