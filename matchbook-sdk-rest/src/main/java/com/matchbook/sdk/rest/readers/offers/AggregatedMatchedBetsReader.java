package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBet;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBetsResponse;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.readers.PageableResponseReader;

import java.util.List;

public class AggregatedMatchedBetsReader
        extends PageableResponseReader<AggregatedMatchedBet, AggregatedMatchedBetsResponse> {

    public AggregatedMatchedBetsReader() {
        super(new AggregatedMatchedBetReader());
    }

    @VisibleForTesting
    AggregatedMatchedBetsReader(AggregatedMatchedBetReader aggregatedMatchedBetReader) {
        super(aggregatedMatchedBetReader);
    }

    @Override
    protected AggregatedMatchedBetsResponse newPageableResponse() {
        return new AggregatedMatchedBetsResponse();
    }

    @Override
    protected String itemsFieldName() {
        return "matched-bets";
    }

    @Override
    public AggregatedMatchedBetsResponse readFullResponse() throws MatchbookSDKParsingException {
        final AggregatedMatchedBetsResponse pageableResponse = newPageableResponse();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("total".equals(fieldName)) {
                pageableResponse.setTotal(parser.getInteger());
            } else if ("offset".equals(fieldName)) {
                pageableResponse.setOffset(parser.getInteger());
            } else if ("per-page".equals(fieldName)) {
                pageableResponse.setPerPage(parser.getInteger());
            } else if ("currency".equals(fieldName)) {
                pageableResponse.setCurrency(parser.getEnum(Currency.class));
            } else if ("exchange-type".equals(fieldName)) {
                pageableResponse.setExchangeType(parser.getEnum(ExchangeType.class));
            } else if ("odds-type".equals(fieldName)) {
                pageableResponse.setOddsType(parser.getEnum(OddsType.class));
            } else if ("matched-bets".equals(fieldName)) {
                List<AggregatedMatchedBet> items = readItems();
                pageableResponse.setItems(items);
            }
            parser.moveToNextToken();
        }
        readingItemStatus = ReadingItemsStatus.READ;
        return pageableResponse;
    }

}
