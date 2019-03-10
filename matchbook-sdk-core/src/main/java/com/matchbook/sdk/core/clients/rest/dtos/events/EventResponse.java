package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookResponse;

public class EventResponse implements MatchbookResponse {

    private Event event;

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return EventResponse.class.getSimpleName() + " {" +
                "event=" + event +
                "}";
    }

}
