package com.matchbook.sdk.rest.dtos.events.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.events.Event;
import com.matchbook.sdk.rest.dtos.events.EventsResponse;

public class EventsResponseReader extends PageableResponseReader<Event, EventsResponse> {

    public EventsResponseReader() {
        super(new EventResponseReader());
    }

    @Override
    protected EventsResponse newPageableResponse() {
        return new EventsResponse();
    }

    @Override
    protected String itemsFieldName() {
        return "events";
    }

}
