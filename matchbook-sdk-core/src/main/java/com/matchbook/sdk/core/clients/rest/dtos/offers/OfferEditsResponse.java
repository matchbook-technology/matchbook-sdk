package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.PageableResponse;

public class OfferEditsResponse extends PageableResponse<OfferEdit> {

    @Override
    public String toString() {
        return OfferEditsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", offerEdits=" + content +
                "}";
    }

}
