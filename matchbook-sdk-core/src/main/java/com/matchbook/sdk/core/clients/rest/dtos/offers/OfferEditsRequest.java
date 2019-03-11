/*
 * Copyright (c) 2019 Triplebet Limited. All right reserved. Inchalla, Le Val, Alderney, GY9 3UL.
 * Company Registration Number: 1827.
 */

package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookPageableRequest;
import com.matchbook.sdk.core.clients.rest.dtos.PageableRequestBuilder;

public class OfferEditsRequest extends MatchbookPageableRequest {

    private final Long offerId;

    private OfferEditsRequest(OfferEditsRequest.Builder builder) {
        super(builder);

        this.offerId = builder.offerId;
    }

    public Long getOfferId() {
        return offerId;
    }

    @Override
    public String toString() {
        return OfferEditsRequest.class.getSimpleName() + " {" +
                "offerId=" + offerId +
                ", offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    public static class Builder extends PageableRequestBuilder {

        private final Long offerId;

        public Builder(Long offerId) {
            this.offerId = offerId;
        }

        public OfferEditsRequest build() {
            return new OfferEditsRequest(this);
        }
    }

}
