package com.matchbook.sdk.core.clients.rest.dtos.prices;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookResponse;

import java.util.List;

public class PricesResponse implements MatchbookResponse {

    private List<Price> prices;

    public List<Price> getPrices() {
        return prices;
    }

    @Override
    public String toString() {
        return PricesResponse.class.getSimpleName() + " {" +
                "prices=" + prices +
                "}";
    }

}
