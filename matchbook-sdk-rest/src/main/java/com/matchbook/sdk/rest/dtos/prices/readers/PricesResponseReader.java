package com.matchbook.sdk.rest.dtos.prices.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.PricesResponse;

public class PricesResponseReader extends PageableResponseReader<Price, PricesResponse> {

    public PricesResponseReader() {
        super(new PriceResponseReader());
    }

    @Override
    protected PricesResponse newPageableResponse() {
        return new PricesResponse();
    }

    @Override
    protected String itemsFieldName() {
        return "prices";
    }

}