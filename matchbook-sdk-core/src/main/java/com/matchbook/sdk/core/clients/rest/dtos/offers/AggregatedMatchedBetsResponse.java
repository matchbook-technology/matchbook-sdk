package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.PageableResponse;

public class AggregatedMatchedBetsResponse extends PageableResponse<AggregatedMatchedBet> {

    @Override
    public String toString() {
        return AggregatedMatchedBetsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", matchedBets=" + content +
                "}";
    }

}
