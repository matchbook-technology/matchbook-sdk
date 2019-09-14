package com.matchbook.sdk.rest.dtos.events.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.events.Event;
import com.matchbook.sdk.rest.dtos.events.EventsResponse;

public class EventsReader extends PageableResponseReader<Event, EventsResponse> {

    public EventsReader() {
        super(new EventReader());
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
