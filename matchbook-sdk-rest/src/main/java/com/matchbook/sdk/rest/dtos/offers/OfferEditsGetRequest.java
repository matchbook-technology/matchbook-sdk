package com.matchbook.sdk.rest.dtos.offers;

import java.util.Map;

import com.matchbook.sdk.rest.dtos.PageableRequest;
import com.matchbook.sdk.rest.dtos.PageableRequestBuilder;

public class OfferEditsGetRequest extends PageableRequest {

    private final Long offerId;

    private OfferEditsGetRequest(OfferEditsGetRequest.Builder builder) {
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
        return OfferEditsGetRequest.class.getSimpleName() + " {" +
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

        public OfferEditsGetRequest build() {
            return new OfferEditsGetRequest(this);
        }
    }

}
