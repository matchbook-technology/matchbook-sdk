package com.matchbook.sdk.rest.dtos.offers;

import com.matchbook.sdk.rest.dtos.PartiallyFailableResponse;

public class OfferEditsResponse extends PartiallyFailableResponse<OfferEdit> {

    @Override
    public String toString() {
        return OfferEditsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", offerEdits=" + items +
                "}";
    }

}
