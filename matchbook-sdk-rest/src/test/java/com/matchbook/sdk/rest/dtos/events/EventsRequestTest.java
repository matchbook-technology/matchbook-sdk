package com.matchbook.sdk.rest.dtos.events;

import com.matchbook.sdk.rest.dtos.PageableRequestTest;

class EventsRequestTest extends PageableRequestTest<EventsRequest> {

    @Override
    protected EventsRequest newPageableRequest(int offset, int perPage) {
        return new EventsRequest.Builder()
                .offset(offset)
                .perPage(perPage)
                .build();
    }

}
