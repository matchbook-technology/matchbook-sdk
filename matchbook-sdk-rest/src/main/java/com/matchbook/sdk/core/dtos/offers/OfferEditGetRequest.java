package com.matchbook.sdk.core.dtos.offers;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.core.dtos.RestRequest;

public class OfferEditGetRequest implements RestRequest {

    private final Long offerEditId;
    private final Long offerId;

    private OfferEditGetRequest(OfferEditGetRequest.Builder builder) {
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
    public String resourcePath() {
        return "v2/offers/" + offerId +
                "/offer-edits/" + offerEditId;
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return OfferEditGetRequest.class.getSimpleName() + " {" +
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

        public OfferEditGetRequest build() {
            return new OfferEditGetRequest(this);
        }
    }

}
