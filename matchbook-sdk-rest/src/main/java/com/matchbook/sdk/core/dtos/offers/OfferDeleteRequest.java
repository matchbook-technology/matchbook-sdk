package com.matchbook.sdk.core.dtos.offers;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.core.dtos.RestRequest;

public class OfferDeleteRequest implements RestRequest {

    private final Long offerId;

    private OfferDeleteRequest(OfferDeleteRequest.Builder builder) {
        this.offerId = builder.offerId;
    }

    public Long getOfferId() {
        return offerId;
    }

    @Override
    public String resourcePath() {
        return "v2/offers/" + offerId;
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return OfferDeleteRequest.class.getSimpleName() + " {" +
                "offerId=" + offerId +
                "}";
    }

    public static class Builder {

        private final Long offerId;

        public Builder(Long offerId) {
            this.offerId = offerId;
        }

        public OfferDeleteRequest build() {
            return new OfferDeleteRequest(this);
        }
    }

}
