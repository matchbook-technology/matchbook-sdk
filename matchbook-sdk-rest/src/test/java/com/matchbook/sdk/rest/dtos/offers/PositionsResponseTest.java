package com.matchbook.sdk.rest.dtos.offers;

import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.PageableResponseTest;

class PositionsResponseTest extends PageableResponseTest<PositionsResponse, Position> {

    @Override
    protected PositionsResponse newResponse() {
        return new PositionsResponse();
    }

    @Override
    protected Position mockItem() {
        return mock(Position.class);
    }

}
