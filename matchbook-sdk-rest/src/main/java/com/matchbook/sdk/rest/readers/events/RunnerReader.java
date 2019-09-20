package com.matchbook.sdk.rest.readers.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.readers.ResponseReader;
import com.matchbook.sdk.rest.dtos.events.Runner;
import com.matchbook.sdk.rest.dtos.events.RunnerStatus;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.readers.prices.PriceReader;

public class RunnerReader extends ResponseReader<Runner> {

    private final PriceReader priceReader;

    public RunnerReader() {
        super();
        priceReader = new PriceReader();
    }

    @Override
    protected Runner readItem() throws MatchbookSDKParsingException {
        final Runner runner = new Runner();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                runner.setId(parser.getLong());
            } else if ("event-id".equals(fieldName)) {
                runner.setEventId(parser.getLong());
            } else if ("market-id".equals(fieldName)) {
                runner.setMarketId(parser.getLong());
            } else if ("event-participant-id".equals(fieldName)) {
                runner.setEventParticipantId(parser.getLong());
            } else if ("name".equals(fieldName)) {
                runner.setName(parser.getString());
            } else if ("status".equals(fieldName)) {
                runner.setStatus(parser.getEnum(RunnerStatus.class));
            } else if ("withdrawn".equals(fieldName)) {
                runner.setWithdrawn(parser.getBoolean());
            } else if ("handicap".equals(fieldName)) {
                runner.setHandicap(parser.getDouble());
            } else if ("volume".equals(fieldName)) {
                runner.setVolume(parser.getDouble());
            } else if ("prices".equals(fieldName)) {
                List<Price> prices = readPrices();
                runner.setPrices(prices);
            }
            parser.moveToNextToken();
        }
        return runner;
    }

    private List<Price> readPrices() {
        List<Price> prices = new ArrayList<>();
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            priceReader.startReading(parser);
            Price price = priceReader.readFullResponse();
            if (Objects.nonNull(price)) {
                prices.add(price);
            }
            parser.moveToNextToken();
        }
        return prices;
    }

}
