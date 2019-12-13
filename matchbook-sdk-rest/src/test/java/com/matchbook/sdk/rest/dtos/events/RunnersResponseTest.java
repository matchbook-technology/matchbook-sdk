package com.matchbook.sdk.rest.dtos.events;

import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.PageableResponseTest;

class RunnersResponseTest extends PageableResponseTest<RunnersResponse, Runner> {

    @Override
    protected RunnersResponse newResponse() {
        return new RunnersResponse();
    }

    @Override
    protected Runner mockItem() {
        return mock(Runner.class);
    }

}
