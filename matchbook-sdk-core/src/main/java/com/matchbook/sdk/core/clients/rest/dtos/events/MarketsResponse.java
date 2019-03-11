package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.PageableResponse;

public class MarketsResponse extends PageableResponse<Market> {

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
