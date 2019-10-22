package com.matchbook.sdk.rest.dtos.events;

import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.PageableResponseTest;

class MarketsResponseTest extends PageableResponseTest<MarketsResponse, Market> {

    @Override
    protected MarketsResponse newPageableResponse() {
        return new MarketsResponse();
    }

    @Override
    protected Market mockItem() {
        return mock(Market.class);
    }

}
