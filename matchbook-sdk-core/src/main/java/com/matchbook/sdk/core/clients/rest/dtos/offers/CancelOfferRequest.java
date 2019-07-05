package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.core.clients.rest.dtos.RestRequest;

public class CancelOfferRequest implements RestRequest {

    private final Long offerId;

    private CancelOfferRequest(CancelOfferRequest.Builder builder) {
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
        return CancelOfferRequest.class.getSimpleName() + " {" +
                ", offerId=" + offerId +
                "}";
    }

    public static class Builder {

        private final Long offerId;

        public Builder(Long offerId) {
            this.offerId = offerId;
        }

        public CancelOfferRequest build() {
            return new CancelOfferRequest(this);
        }
    }

}
