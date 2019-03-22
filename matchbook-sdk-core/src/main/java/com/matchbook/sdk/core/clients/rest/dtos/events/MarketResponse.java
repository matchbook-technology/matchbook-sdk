package com.matchbook.sdk.core.clients.rest.dtos.events;

import java.util.Collections;
import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class MarketResponse implements RestResponse<Market> {

    private Market market;

    public Market getMarket() {
        return market;
    }

    @Override
    public List<Market> getContent() {
        return Collections.singletonList(market);
    }

    @Override
    public String toString() {
        return MarketResponse.class.getSimpleName() + " {" +
                "market=" + market +
                "}";
    }

}
