package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookPageableResponse;

public class EventsResponse extends MatchbookPageableResponse<Event> {

    @Override
    public String toString() {
        return EventsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", events=" + content +
                "}";
    }

}
