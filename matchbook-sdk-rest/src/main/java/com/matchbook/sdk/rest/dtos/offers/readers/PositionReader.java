package com.matchbook.sdk.rest.dtos.offers.readers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.ResponseReader;
import com.matchbook.sdk.rest.dtos.offers.Position;

public class PositionReader extends ResponseReader<Position> {

    public PositionReader() {
        super();
    }

    @Override
    protected Position readItem() throws MatchbookSDKParsingException {
        final Position position = new Position();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("event-id".equals(fieldName)) {
                position.setEventId(parser.getLong());
            } else if ("market-id".equals(fieldName)) {
                position.setMarketId(parser.getLong());
            } else if ("runner-id".equals(fieldName)) {
                position.setRunnerId(parser.getLong());
            } else if ("potential-profit".equals(fieldName)) {
                position.setPotentialProfit(parser.getDecimal());
            } else if ("potential-loss".equals(fieldName)) {
                position.setPotentialLoss(parser.getDecimal());
            }
            parser.moveToNextToken();
        }
        return position;
    }

}
