package com.matchbook.sdk.rest.dtos.offers;

import java.util.Map;

import com.matchbook.sdk.rest.dtos.PageableRequest;

public class OfferEditsGetRequest extends PageableRequest {

    private final Long offerId;

    private OfferEditsGetRequest(Init<?> builder) {
        super(builder);

        this.offerId = builder.offerId;
    }

    protected static abstract class Init<T extends Init<T>> extends PageableRequest.Init<T> {

        private Long offerId;

        public T offerID(Long offerId) {
            this.offerId = offerId;
            return self();
        }

        public OfferEditsGetRequest build() {
            return new OfferEditsGetRequest(this);
        }
    }


    public static class Builder extends Init<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
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
}
