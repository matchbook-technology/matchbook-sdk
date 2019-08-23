package com.matchbook.sdk.rest.dtos.events.readers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.events.Runner;
import com.matchbook.sdk.rest.dtos.events.RunnerStatus;
import com.matchbook.sdk.rest.dtos.events.RunnersResponse;

public class RunnersResponseReader extends PageableResponseReader<Runner, RunnersResponse> {

    public RunnersResponseReader() {
        super();
    }

    @Override
    protected String getItemsFieldName() {
        return "runners";
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
            } else if ("volume".equals(fieldName)) {
                runner.setVolume(parser.getDecimal());
            } else if ("prices".equals(fieldName)) {
                // FIXME
                parser.skipChildren();
            }
            parser.moveToNextToken();
        }
        return runner;
    }

}
