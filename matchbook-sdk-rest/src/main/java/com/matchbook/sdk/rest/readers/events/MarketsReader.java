package com.matchbook.sdk.rest.readers.events;

import com.matchbook.sdk.rest.dtos.events.Market;
import com.matchbook.sdk.rest.dtos.events.MarketsResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReader;

public class MarketsReader extends PageableResponseReader<Market, MarketsResponse> {

    public MarketsReader() {
        super(new MarketReader());
    }

    @Override
    protected MarketsResponse newPageableResponse() {
        return new MarketsResponse();
    }

    @Override
    protected String itemsFieldName() {
        return "markets";
    }

}
