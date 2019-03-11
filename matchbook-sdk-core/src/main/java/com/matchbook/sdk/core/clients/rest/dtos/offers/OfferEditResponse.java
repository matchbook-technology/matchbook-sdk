package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class OfferEditResponse implements RestResponse {

    private OfferEdit offerEdit;

    public OfferEdit getOfferEdit() {
        return offerEdit;
    }

    @Override
    public String toString() {
        return OfferEditResponse.class.getSimpleName() + " {" +
                "offerEdit=" + offerEdit +
                "}";
    }

}
