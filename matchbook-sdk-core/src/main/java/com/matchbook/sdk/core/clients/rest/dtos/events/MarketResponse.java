package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookResponse;

public class MarketResponse implements MatchbookResponse {

    private Market market;

    public Market getMarket() {
        return market;
    }

    @Override
    public String toString() {
        return MarketResponse.class.getSimpleName() + " {" +
                "market=" + market +
                "}";
    }

}
