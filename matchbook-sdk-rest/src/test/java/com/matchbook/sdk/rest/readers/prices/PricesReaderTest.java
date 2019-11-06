package com.matchbook.sdk.rest.readers.prices;

import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.PricesResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReaderTest;

import org.mockito.Mock;

class PricesReaderTest extends PageableResponseReaderTest<PricesReader, Price, PricesResponse> {

    @Mock
    private PriceReader priceReader;

    @Mock
    private Price price;

    @Override
    protected PricesReader newReader() {
        return new PricesReader(priceReader);
    }

    @Override
    protected PriceReader getItemsReader() {
        return priceReader;
    }

    @Override
    protected Price getItem() {
        return price;
    }

    @Override
    protected String getItemsFieldName() {
        return "prices";
    }

}
