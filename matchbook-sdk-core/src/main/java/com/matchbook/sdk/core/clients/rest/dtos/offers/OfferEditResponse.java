package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.util.Collections;
import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class OfferEditResponse implements RestResponse<OfferEdit> {

    private OfferEdit offerEdit;

    public OfferEdit getOfferEdit() {
        return offerEdit;
    }

    @Override
    public List<OfferEdit> getContent() {
        return Collections.singletonList(offerEdit);
    }

    @Override
    public String toString() {
        return OfferEditResponse.class.getSimpleName() + " {" +
                "offerEdit=" + offerEdit +
                "}";
    }

}
