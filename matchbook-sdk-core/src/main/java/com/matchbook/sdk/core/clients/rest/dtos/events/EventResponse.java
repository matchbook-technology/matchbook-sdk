package com.matchbook.sdk.core.clients.rest.dtos.events;

import java.util.Collections;
import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class EventResponse implements RestResponse<Event> {

    private Event event;

    public Event getEvent() {
        return event;
    }

    @Override
    public List<Event> getContent() {
        return Collections.singletonList(event);
    }

    @Override
    public String toString() {
        return EventResponse.class.getSimpleName() + " {" +
                "event=" + event +
                "}";
    }

}
