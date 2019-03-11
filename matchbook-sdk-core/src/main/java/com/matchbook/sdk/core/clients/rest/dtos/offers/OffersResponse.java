package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.PageableResponse;

public class OffersResponse extends PageableResponse<Offer> {

    @Override
    public String toString() {
        return OffersResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", offers=" + content +
                "}";
    }

}
