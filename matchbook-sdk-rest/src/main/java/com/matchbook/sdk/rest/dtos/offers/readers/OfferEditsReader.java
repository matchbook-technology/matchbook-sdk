package com.matchbook.sdk.rest.dtos.offers.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditsResponse;

public class OfferEditsReader extends PageableResponseReader<OfferEdit, OfferEditsResponse> {

    public OfferEditsReader() {
        super(new OfferEditReader());
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
