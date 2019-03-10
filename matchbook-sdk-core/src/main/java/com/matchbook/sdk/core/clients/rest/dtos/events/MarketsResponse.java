package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookPageableResponse;

public class MarketsResponse extends MatchbookPageableResponse<Market> {

    @Override
    public String toString() {
        return MarketsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", markets=" + content +
                "}";
    }

}
