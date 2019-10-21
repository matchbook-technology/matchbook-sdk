package com.matchbook.sdk.rest.dtos.offers;

import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.PartiallyFailableResponseTest;

class OffersResponseTest extends PartiallyFailableResponseTest<OffersResponse, Offer> {

    @Override
    protected OffersResponse newPageableResponse() {
        return new OffersResponse();
    }

    @Override
    protected Offer mockItem() {
        return mock(Offer.class);
    }

}
