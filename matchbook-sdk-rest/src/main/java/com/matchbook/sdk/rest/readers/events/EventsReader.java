package com.matchbook.sdk.rest.readers.events;

import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.dtos.events.Event;
import com.matchbook.sdk.rest.dtos.events.EventsResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReader;

public class EventsReader extends PageableResponseReader<Event, EventsResponse> {

    public EventsReader() {
        super(new EventReader());
    }

    @VisibleForTesting
    EventsReader(EventReader eventReader) {
        super(eventReader);
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
