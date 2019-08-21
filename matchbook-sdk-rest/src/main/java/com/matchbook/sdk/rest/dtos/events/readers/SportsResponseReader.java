package com.matchbook.sdk.rest.dtos.events.readers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.AbstractReader;
import com.matchbook.sdk.rest.dtos.events.Sport;
import com.matchbook.sdk.rest.dtos.events.SportsResponse;

public class SportsResponseReader extends AbstractReader<Sport, SportsResponse> {

    public SportsResponseReader() {
        super();
    }

    @Override
    protected String getItemsFieldName() {
        return "sports";
    }

    @Override
    protected Sport readItem() throws MatchbookSDKParsingException {
        parser.startObject();
        final Sport sport = new Sport();
        while (parser.hasNext()) {
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                sport.setId(parser.getLong());
            } else if ("name".equals(fieldName)) {
                sport.setName(parser.getString());
            }
        }
        return sport;
    }

    @Override
    public SportsResponse readFull() throws MatchbookSDKParsingException {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

}
