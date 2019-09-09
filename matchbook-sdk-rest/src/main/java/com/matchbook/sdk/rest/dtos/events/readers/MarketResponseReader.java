package com.matchbook.sdk.rest.dtos.events.readers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.ResponseReader;
import com.matchbook.sdk.rest.dtos.events.Market;
import com.matchbook.sdk.rest.dtos.events.MarketStatus;
import com.matchbook.sdk.rest.dtos.events.MarketType;
import com.matchbook.sdk.rest.dtos.events.Runner;

public class MarketResponseReader extends ResponseReader<Market> {

    private final RunnerResponseReader runnerReader;

    public MarketResponseReader() {
        super();
        runnerReader = new RunnerResponseReader();
    }

    @Override
    protected Market readItem() throws MatchbookSDKParsingException {
        final Market market = new Market();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                market.setId(parser.getLong());
            } else if ("event-id".equals(fieldName)) {
                market.setEventId(parser.getLong());
            } else if ("name".equals(fieldName)) {
                market.setName(parser.getString());
            } else if ("market-type".equals(fieldName)) {
                market.setMarketType(parser.getEnum(MarketType.class));
            } else if ("status".equals(fieldName)) {
                market.setStatus(parser.getEnum(MarketStatus.class));
            } else if ("in-running-flag".equals(fieldName)) {
                market.setInRunning(parser.getBoolean());
            } else if ("allow-live-betting".equals(fieldName)) {
                market.setAllowLiveBetting(parser.getBoolean());
            } else if ("handicap".equals(fieldName)) {
                market.setHandicap(parser.getDouble());
            } else if ("volume".equals(fieldName)) {
                market.setVolume(parser.getDouble());
            } else if ("winners".equals(fieldName)) {
                market.setWinners(parser.getInteger());
            } else if ("back-overround".equals(fieldName)) {
                market.setBackOverround(parser.getDecimal());
            } else if ("lay-overround".equals(fieldName)) {
                market.setLayOverround(parser.getDecimal());
            } else if ("runners".equals(fieldName)) {
                List<Runner> runners = readRunners();
                market.setRunners(runners);
            }
            parser.moveToNextToken();
        }
        return market;
    }

    private List<Runner> readRunners() {
        List<Runner> runners = new ArrayList<>();
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            runnerReader.startReading(parser);
            Runner runner = runnerReader.readFullResponse();
            if (Objects.nonNull(runner)) {
                runners.add(runner);
            }
            parser.moveToNextToken();
        }
        return runners;
    }

}
