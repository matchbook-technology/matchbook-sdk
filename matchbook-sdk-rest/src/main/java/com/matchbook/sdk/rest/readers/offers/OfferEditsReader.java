package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditsResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReader;

public class OfferEditsReader extends PageableResponseReader<OfferEdit, OfferEditsResponse> {

    public OfferEditsReader() {
        super(new OfferEditReader());
    }

    @VisibleForTesting
    OfferEditsReader(OfferEditReader offerEditReader) {
        super(offerEditReader);
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
