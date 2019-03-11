package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.PageableResponse;

public class CancelledMatchedBetsResponse extends PageableResponse<MatchedBet> {

    @Override
    public String toString() {
        return CancelledMatchedBetsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", matchedBets=" + content +
                "}";
    }

}
