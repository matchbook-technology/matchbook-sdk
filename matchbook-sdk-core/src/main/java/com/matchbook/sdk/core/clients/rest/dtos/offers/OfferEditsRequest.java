package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.util.Map;

import com.matchbook.sdk.core.clients.rest.dtos.PageableRequest;
import com.matchbook.sdk.core.clients.rest.dtos.PageableRequestBuilder;

public class OfferEditsRequest extends PageableRequest {

    private final Long offerId;

    private OfferEditsRequest(OfferEditsRequest.Builder builder) {
        super(builder);

        this.offerId = builder.offerId;
    }

    public Long getOfferId() {
        return offerId;
    }

    @Override
    public String resourcePath() {
        return "v2/offers/" + offerId + "/offer-edits";
    }

    @Override
    public Map<String, String> parameters() {
        return pageParameters();
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
