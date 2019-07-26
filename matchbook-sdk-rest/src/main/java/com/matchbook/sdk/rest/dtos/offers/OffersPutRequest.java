package com.matchbook.sdk.rest.dtos.offers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.RestRequest;

public class OffersPutRequest implements RestRequest {

    private final List<OfferPutRequest> offers;

    private OffersPutRequest(OffersPutRequest.Builder builder) {
        this.offers = builder.offers;
    }

    public List<OfferPutRequest> getOffers() {
        return offers;
    }

    @Override
    public String resourcePath() {
        return "v2/offers";
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return OffersPutRequest.class.getSimpleName() + " {" +
                "offers=" + offers +
                "}";
    }

    public static class Builder {

        private final List<OfferPutRequest> offers;

        public Builder(List<OfferPutRequest> offers) {
            this.offers = offers;
        }

        public OffersPutRequest build() {
            return new OffersPutRequest(this);
        }

    }

}
