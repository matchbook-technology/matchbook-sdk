package com.matchbook.sdk.rest.dtos.events.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.events.Market;
import com.matchbook.sdk.rest.dtos.events.MarketsResponse;

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
