package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditsResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReaderTest;

import org.mockito.Mock;

class OfferEditsReaderTest extends PageableResponseReaderTest<OfferEditsReader, OfferEdit, OfferEditsResponse> {

    @Mock
    private OfferEditReader offerEditReader;

    @Mock
    private OfferEdit offerEdit;

    @Override
    protected OfferEditsReader newReader() {
        return new OfferEditsReader(offerEditReader);
    }

    @Override
    protected OfferEditReader getItemsReader() {
        return offerEditReader;
    }

    @Override
    protected OfferEdit getItem() {
        return offerEdit;
    }

    @Override
    protected String getItemsFieldName() {
        return "offer-edits";
    }

}
