/*
 * Copyright (c) 2019 Triplebet Limited. All right reserved. Inchalla, Le Val, Alderney, GY9 3UL.
 * Company Registration Number: 1827.
 */

package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookResponse;

public class OfferEditResponse implements MatchbookResponse {

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
