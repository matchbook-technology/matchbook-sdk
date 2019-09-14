package com.matchbook.sdk.rest.dtos.prices;

import com.matchbook.sdk.rest.dtos.PageableResponse;

public class PricesResponse extends PageableResponse<Price> {

    @Override
    public String toString() {
        return PricesResponse.class.getSimpleName() + " {" +
                "prices=" + items +
                "}";
    }

}
