package com.matchbook.sdk.rest.readers.offers;

import java.util.List;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.readers.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.offers.Offer;
import com.matchbook.sdk.rest.dtos.offers.OffersResponse;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;

public class OffersReader extends PageableResponseReader<Offer, OffersResponse> {

    public OffersReader() {
        super(new OfferReader());
    }

    @Override
    protected OffersResponse newPageableResponse() {
        return new OffersResponse();
    }

    @Override
    protected String itemsFieldName() {
        return "offers";
    }

    @Override
    public OffersResponse readFullResponse() throws MatchbookSDKParsingException {
        final OffersResponse pageableResponse = new OffersResponse();
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
            } else if (itemsFieldName().equals(fieldName)) {
                List<Offer> items = readItems();
                pageableResponse.setItems(items);
            }
            parser.moveToNextToken();
        }
        return pageableResponse;
    }

}
