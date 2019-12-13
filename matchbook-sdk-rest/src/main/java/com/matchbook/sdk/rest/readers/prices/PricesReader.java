package com.matchbook.sdk.rest.readers.prices;

import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.PricesResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReader;

public class PricesReader extends PageableResponseReader<Price, PricesResponse> {

    public PricesReader() {
        super(new PriceReader());
    }

    @VisibleForTesting
    PricesReader(PriceReader priceReader) {
        super(priceReader);
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
