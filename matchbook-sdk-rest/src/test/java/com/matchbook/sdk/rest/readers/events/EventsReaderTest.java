package com.matchbook.sdk.rest.readers.events;

import com.matchbook.sdk.rest.dtos.events.Event;
import com.matchbook.sdk.rest.dtos.events.EventsResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReaderTest;

import org.mockito.Mock;

class EventsReaderTest extends PageableResponseReaderTest<EventsReader, Event, EventsResponse> {

    @Mock
    private EventReader eventReader;

    @Mock
    private Event event;

    @Override
    protected EventsReader newReader() {
        return new EventsReader(eventReader);
    }

    @Override
    protected EventReader getItemsReader() {
        return eventReader;
    }

    @Override
    protected Event getItem() {
        return event;
    }

    @Override
    protected String getItemsFieldName() {
        return "events";
    }

}
