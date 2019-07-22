package com.matchbook.sdk.core.dtos.offers;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.core.dtos.RestRequest;

public class OfferGetRequest implements RestRequest {

    private final Long offerId;
    private final boolean includeEdits;

    private OfferGetRequest(OfferGetRequest.Builder builder) {
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
    public String resourcePath() {
        return "v2/offers/" + offerId;
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.singletonMap("include-edits", String.valueOf(includeEdits));
    }

    @Override
    public String toString() {
        return OfferGetRequest.class.getSimpleName() + " {" +
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

        public OfferGetRequest build() {
            return new OfferGetRequest(this);
        }
    }

}
