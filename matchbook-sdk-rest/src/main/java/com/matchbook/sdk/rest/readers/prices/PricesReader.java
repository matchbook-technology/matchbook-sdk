package com.matchbook.sdk.rest.readers.prices;

import com.matchbook.sdk.rest.readers.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.PricesResponse;

public class PricesReader extends PageableResponseReader<Price, PricesResponse> {

    public PricesReader() {
        super(new PriceReader());
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
