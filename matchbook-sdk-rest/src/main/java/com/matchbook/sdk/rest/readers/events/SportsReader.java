package com.matchbook.sdk.rest.readers.events;

import com.matchbook.sdk.rest.dtos.events.Sport;
import com.matchbook.sdk.rest.dtos.events.SportsResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReader;

public class SportsReader extends PageableResponseReader<Sport, SportsResponse> {

    public SportsReader() {
        super(new SportReader());
    }

    @Override
    protected SportsResponse newPageableResponse() {
        return new SportsResponse();
    }

    @Override
    protected String itemsFieldName() {
        return "sports";
    }

}
