package com.matchbook.sdk.rest.readers.events;

import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.dtos.events.Market;
import com.matchbook.sdk.rest.dtos.events.MarketsResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReader;

public class MarketsReader extends PageableResponseReader<Market, MarketsResponse> {

    public MarketsReader() {
        super(new MarketReader());
    }

    @VisibleForTesting
    MarketsReader(MarketReader marketReader) {
        super(marketReader);
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
