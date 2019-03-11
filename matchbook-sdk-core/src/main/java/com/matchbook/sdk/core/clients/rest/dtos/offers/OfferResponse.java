package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class OfferResponse implements RestResponse {

    private Offer offer;

    public Offer getOffer() {
        return offer;
    }

    @Override
    public String toString() {
        return OfferResponse.class.getSimpleName() + " {" +
                "offer=" + offer +
                "}";
    }

}
