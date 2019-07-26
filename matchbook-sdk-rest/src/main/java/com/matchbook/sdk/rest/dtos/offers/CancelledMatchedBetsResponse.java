package com.matchbook.sdk.rest.dtos.offers;

import com.matchbook.sdk.rest.dtos.PageableResponse;

import java.util.Collection;
import java.util.List;

public class CancelledMatchedBetsResponse extends PageableResponse<MatchedBet> {

    private List<MatchedBet> matchedBets;

    public List<MatchedBet> getMatchedBets() {
        return matchedBets;
    }

    public void setMatchedBets(List<MatchedBet> matchedBets) {
        this.matchedBets = matchedBets;
    }

    @Override
    public Collection<MatchedBet> getContent() {
        return matchedBets;
    }

    @Override
    public String toString() {
        return CancelledMatchedBetsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", matchedBets=" + matchedBets +
                "}";
    }

}
