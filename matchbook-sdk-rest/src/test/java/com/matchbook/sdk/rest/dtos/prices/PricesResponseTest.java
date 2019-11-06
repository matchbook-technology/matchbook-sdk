package com.matchbook.sdk.rest.dtos.prices;

import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.PageableResponseTest;

class PricesResponseTest extends PageableResponseTest<PricesResponse, Price> {

    @Override
    protected PricesResponse newResponse() {
        return new PricesResponse();
    }

    @Override
    protected Price mockItem() {
        return mock(Price.class);
    }

}
