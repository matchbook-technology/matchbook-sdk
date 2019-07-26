package com.matchbook.sdk.rest.dtos.events;

import com.matchbook.sdk.rest.dtos.PageableResponse;

import java.util.Collection;
import java.util.List;

public class EventsResponse extends PageableResponse<Event> {

    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public Collection<Event> getContent() {
        return events;
    }

    @Override
    public String toString() {
        return EventsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", events=" + events +
                "}";
    }

}
