package com.matchbook.sdk.rest.dtos.events;

import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.PageableResponseTest;

class SportsResponseTest extends PageableResponseTest<SportsResponse, Sport> {

    @Override
    protected SportsResponse newResponse() {
        return new SportsResponse();
    }

    @Override
    protected Sport mockItem() {
        return mock(Sport.class);
    }

}
