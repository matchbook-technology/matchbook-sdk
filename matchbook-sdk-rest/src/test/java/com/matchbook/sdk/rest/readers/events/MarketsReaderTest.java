package com.matchbook.sdk.rest.readers.events;

import com.matchbook.sdk.rest.dtos.events.Market;
import com.matchbook.sdk.rest.dtos.events.MarketsResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReaderTest;

import org.mockito.Mock;

class MarketsReaderTest extends PageableResponseReaderTest<MarketsReader, Market, MarketsResponse> {

    @Mock
    private MarketReader marketReader;

    @Mock
    private Market market;

    @Override
    protected MarketsReader newReader() {
        return new MarketsReader(marketReader);
    }

    @Override
    protected MarketReader getItemsReader() {
        return marketReader;
    }

    @Override
    protected Market getItem() {
        return market;
    }

    @Override
    protected String getItemsFieldName() {
        return "markets";
    }

}
