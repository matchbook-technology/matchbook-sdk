/*
 * Copyright (c) 2019 Triplebet Limited. All right reserved. Inchalla, Le Val, Alderney, GY9 3UL.
 * Company Registration Number: 1827.
 */

package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookResponse;

public class OfferResponse implements MatchbookResponse {

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
