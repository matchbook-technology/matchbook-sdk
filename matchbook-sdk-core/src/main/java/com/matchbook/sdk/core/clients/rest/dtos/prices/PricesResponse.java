package com.matchbook.sdk.core.clients.rest.dtos.prices;

import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class PricesResponse implements RestResponse<Price> {

    private List<Price> prices;

    @Override
    public List<Price> getContent() {
        return prices;
    }

    @Override
    public String toString() {
        return PricesResponse.class.getSimpleName() + " {" +
                "prices=" + prices +
                "}";
    }

}
