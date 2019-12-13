package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBet;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.Side;
import com.matchbook.sdk.rest.readers.ResponseReader;

public class AggregatedMatchedBetReader extends ResponseReader<AggregatedMatchedBet> {

    public AggregatedMatchedBetReader() {
        super();
    }

    @Override
    protected AggregatedMatchedBet readItem() throws MatchbookSDKParsingException {
        final AggregatedMatchedBet aggregatedMatchedBet = new AggregatedMatchedBet();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("event-id".equals(fieldName)) {
                aggregatedMatchedBet.setEventId(parser.getLong());
            } else if ("market-id".equals(fieldName)) {
                aggregatedMatchedBet.setMarketId(parser.getLong());
            } else if ("runner-id".equals(fieldName)) {
                aggregatedMatchedBet.setRunnerId(parser.getLong());
            } else if ("side".equals(fieldName)) {
                aggregatedMatchedBet.setSide(parser.getEnum(Side.class));
            } else if ("event-name".equals(fieldName)) {
                aggregatedMatchedBet.setEventName(parser.getString());
            } else if ("market-name".equals(fieldName)) {
                aggregatedMatchedBet.setMarketName(parser.getString());
            } else if ("runner-name".equals(fieldName)) {
                aggregatedMatchedBet.setRunnerName(parser.getString());
            } else if ("odds-type".equals(fieldName)) {
                aggregatedMatchedBet.setOddsType(parser.getEnum(OddsType.class));
            } else if ("odds".equals(fieldName)) {
                aggregatedMatchedBet.setOdds(parser.getDouble());
            } else if ("decimal-odds".equals(fieldName)) {
                aggregatedMatchedBet.setDecimalOdds(parser.getDouble());
            } else if ("stake".equals(fieldName)) {
                aggregatedMatchedBet.setStake(parser.getDecimal());
            } else if ("potential-profit".equals(fieldName)) {
                aggregatedMatchedBet.setPotentialProfit(parser.getDecimal());
            } else if ("potential-liability".equals(fieldName)) {
                aggregatedMatchedBet.setPotentialLiability(parser.getDecimal());
            }
            parser.moveToNextToken();
        }
        return aggregatedMatchedBet;
    }

}
