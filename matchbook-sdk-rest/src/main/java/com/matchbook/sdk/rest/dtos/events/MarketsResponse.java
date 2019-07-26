package com.matchbook.sdk.rest.dtos.events;

import com.matchbook.sdk.rest.dtos.PageableResponse;

import java.util.Collection;
import java.util.List;

public class MarketsResponse extends PageableResponse<Market> {

    private List<Market> markets;

    public List<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    @Override
    public Collection<Market> getContent() {
        return markets;
    }

    @Override
    public String toString() {
        return MarketsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", markets=" + markets +
                "}";
    }

}
