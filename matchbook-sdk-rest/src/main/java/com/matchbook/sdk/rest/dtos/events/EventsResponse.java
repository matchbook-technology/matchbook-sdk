package com.matchbook.sdk.rest.dtos.events;

import com.matchbook.sdk.rest.dtos.PageableResponse;

public class EventsResponse extends PageableResponse<Event> {

    @Override
    public String toString() {
        return EventsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", events=" + items +
                "}";
    }

}
