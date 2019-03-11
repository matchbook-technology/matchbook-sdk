/*
 * Copyright (c) 2019 Triplebet Limited. All right reserved. Inchalla, Le Val, Alderney, GY9 3UL.
 * Company Registration Number: 1827.
 */

package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookRequest;

public class OfferEditRequest implements MatchbookRequest {

    private final Long offerEditId;
    private final Long offerId;

    private OfferEditRequest(OfferEditRequest.Builder builder) {
        this.offerEditId = builder.offerEditId;
        this.offerId = builder.offerId;
    }

    public Long getOfferEditId() {
        return offerEditId;
    }

    public Long getOfferId() {
        return offerId;
    }

    @Override
    public String toString() {
        return OfferEditRequest.class.getSimpleName() + " {" +
                "offerEditId=" + offerEditId +
                ", offerId=" + offerId +
                "}";
    }

    public static class Builder {

        private final Long offerEditId;
        private final Long offerId;

        public Builder(Long offerEditId, Long offerId) {
            this.offerEditId = offerEditId;
            this.offerId = offerId;
        }

        public OfferEditRequest build() {
            return new OfferEditRequest(this);
        }
    }

}
