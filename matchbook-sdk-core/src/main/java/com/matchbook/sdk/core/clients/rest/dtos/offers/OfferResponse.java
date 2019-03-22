package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.util.Collections;
import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class OfferResponse implements RestResponse<Offer> {

    private Offer offer;

    public Offer getOffer() {
        return offer;
    }

    @Override
    public List<Offer> getContent() {
        return Collections.singletonList(offer);
    }

    @Override
    public String toString() {
        return OfferResponse.class.getSimpleName() + " {" +
                "offer=" + offer +
                "}";
    }

}
