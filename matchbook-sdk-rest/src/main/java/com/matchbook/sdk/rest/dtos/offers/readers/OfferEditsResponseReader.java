package com.matchbook.sdk.rest.dtos.offers.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditsResponse;

public class OfferEditsResponseReader extends PageableResponseReader<OfferEdit, OfferEditsResponse> {

    public OfferEditsResponseReader() {
        super(new OfferEditResponseReader());
    }

    @Override
    protected OfferEditsResponse newPageableResponse() {
        return new OfferEditsResponse();
    }

    @Override
    protected String itemsFieldName() {
        return "offer-edits";
    }

}
