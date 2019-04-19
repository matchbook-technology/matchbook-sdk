package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.PageableResponse;

import java.util.Collection;
import java.util.List;

public class OffersResponse extends PageableResponse<Offer> {

    private List<Offer> offers;

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public Collection<Offer> getContent() {
        return offers;
    }

    @Override
    public String toString() {
        return OffersResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", offers=" + offers +
                "}";
    }

}
