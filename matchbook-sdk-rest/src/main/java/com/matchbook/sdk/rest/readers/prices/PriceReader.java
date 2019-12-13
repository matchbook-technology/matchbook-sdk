package com.matchbook.sdk.rest.readers.prices;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.Side;
import com.matchbook.sdk.rest.readers.ResponseReader;

public class PriceReader extends ResponseReader<Price> {

    public PriceReader() {
        super();
    }

    @Override
    protected Price readItem() throws MatchbookSDKParsingException {
        final Price price = new Price();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("exchange-type".equals(fieldName)) {
                price.setExchangeType(parser.getEnum(ExchangeType.class));
            } else if ("side".equals(fieldName)) {
                price.setSide(parser.getEnum(Side.class));
            } else if ("odds-type".equals(fieldName)) {
                price.setOddsType(parser.getEnum(OddsType.class));
            } else if ("currency".equals(fieldName)) {
                price.setCurrency(parser.getEnum(Currency.class));
            } else if ("odds".equals(fieldName)) {
                price.setOdds(parser.getDouble());
            } else if ("available-amount".equals(fieldName)) {
                price.setAvailableAmount(parser.getDecimal());
            }
            parser.moveToNextToken();
        }
        return price;
    }

}
