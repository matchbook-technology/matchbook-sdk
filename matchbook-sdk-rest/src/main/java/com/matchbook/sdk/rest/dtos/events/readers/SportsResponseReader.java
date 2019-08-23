package com.matchbook.sdk.rest.dtos.events.readers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.events.Sport;
import com.matchbook.sdk.rest.dtos.events.SportsResponse;

public class SportsResponseReader extends PageableResponseReader<Sport, SportsResponse> {

    public SportsResponseReader() {
        super();
    }

    @Override
    protected String getItemsFieldName() {
        return "sports";
    }

    @Override
    protected Sport readItem() throws MatchbookSDKParsingException {
        final Sport sport = new Sport();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                sport.setId(parser.getLong());
            } else if ("name".equals(fieldName)) {
                sport.setName(parser.getString());
            }
            parser.moveToNextToken();
        }
        return sport;
    }

}
