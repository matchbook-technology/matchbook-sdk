package com.matchbook.sdk.rest.dtos.offers;

import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.PartiallyFailableResponseTest;

class OfferEditsResponseTest extends PartiallyFailableResponseTest<OfferEditsResponse, OfferEdit> {

    @Override
    protected OfferEditsResponse newPageableResponse() {
        return new OfferEditsResponse();
    }

    @Override
    protected OfferEdit mockItem() {
        return mock(OfferEdit.class);
    }

}
