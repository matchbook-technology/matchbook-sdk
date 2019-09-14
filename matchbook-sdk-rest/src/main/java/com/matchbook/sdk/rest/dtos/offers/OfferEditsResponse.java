package com.matchbook.sdk.rest.dtos.offers;

import com.matchbook.sdk.rest.dtos.PageableResponse;

public class OfferEditsResponse extends PageableResponse<OfferEdit> {

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
