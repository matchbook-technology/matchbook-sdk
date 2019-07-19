package com.matchbook.sdk.stream.model.dataobjects;

public class EventEnvelope implements Envelope {

    private EventMessageType eventMessageType;

    public EventMessageType getEventMessageType() {
        return eventMessageType;
    }
}
