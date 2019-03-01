package com.matchbook.sdk.clients.rest.dtos.events;

import com.matchbook.sdk.clients.rest.dtos.MatchbookResponse;

public class MarketResponse implements MatchbookResponse {

    private Market market;

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

}
