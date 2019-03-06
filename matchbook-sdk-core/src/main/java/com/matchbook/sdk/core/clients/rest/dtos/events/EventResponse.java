package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookResponse;

public class EventResponse implements MatchbookResponse {

    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
