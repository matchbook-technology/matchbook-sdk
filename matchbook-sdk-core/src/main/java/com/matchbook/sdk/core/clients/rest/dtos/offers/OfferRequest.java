package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.RestRequest;

public class OfferRequest implements RestRequest {

    private final Long offerId;
    private final boolean includeEdits;

    private OfferRequest(OfferRequest.Builder builder) {
        this.offerId = builder.eventId;
        this.includeEdits = builder.includeEdits;
    }

    public Long getOfferId() {
        return offerId;
    }

    public boolean includeEdits() {
        return includeEdits;
    }

    @Override
    public String toString() {
        return OfferRequest.class.getSimpleName() + " {" +
                "offerId=" + offerId +
                ", includeEdits=" + includeEdits +
                "}";
    }

    public static class Builder {

        private final Long eventId;
        private boolean includeEdits;

        public Builder(Long eventId) {
            this.eventId = eventId;
            includeEdits = false;
        }

        public Builder includeEdits(boolean includeEdits) {
            this.includeEdits = includeEdits;
            return this;
        }

        public OfferRequest build() {
            return new OfferRequest(this);
        }
    }

}
