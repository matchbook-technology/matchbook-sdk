package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.PageableResponse;

import java.util.Collection;
import java.util.List;

public class AggregatedMatchedBetsResponse extends PageableResponse<AggregatedMatchedBet> {

    private List<AggregatedMatchedBet> matchedBets;

    public List<AggregatedMatchedBet> getMatchedBets() {
        return matchedBets;
    }

    public void setMatchedBets(List<AggregatedMatchedBet> matchedBets) {
        this.matchedBets = matchedBets;
    }

    @Override
    public Collection<AggregatedMatchedBet> getContent() {
        return matchedBets;
    }

    @Override
    public String toString() {
        return AggregatedMatchedBetsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", matchedBets=" + matchedBets +
                "}";
    }

}
