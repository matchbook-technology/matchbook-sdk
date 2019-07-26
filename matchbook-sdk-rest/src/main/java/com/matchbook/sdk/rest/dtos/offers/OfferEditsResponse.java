package com.matchbook.sdk.rest.dtos.offers;

import com.matchbook.sdk.rest.dtos.PageableResponse;

import java.util.Collection;
import java.util.List;

public class OfferEditsResponse extends PageableResponse<OfferEdit> {

    private List<OfferEdit> offerEdits;

    public List<OfferEdit> getOfferEdits() {
        return offerEdits;
    }

    public void setOfferEdits(List<OfferEdit> offerEdits) {
        this.offerEdits = offerEdits;
    }

    @Override
    public Collection<OfferEdit> getContent() {
        return offerEdits;
    }

    @Override
    public String toString() {
        return OfferEditsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", offerEdits=" + offerEdits +
                "}";
    }

}
