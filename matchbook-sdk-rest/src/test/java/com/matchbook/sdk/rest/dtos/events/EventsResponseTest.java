package com.matchbook.sdk.rest.dtos.events;

import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.PageableResponseTest;

class EventsResponseTest extends PageableResponseTest<EventsResponse, Event> {

    @Override
    protected EventsResponse newPageableResponse() {
        return new EventsResponse();
    }

    @Override
    protected Event mockItem() {
        return mock(Event.class);
    }

}
