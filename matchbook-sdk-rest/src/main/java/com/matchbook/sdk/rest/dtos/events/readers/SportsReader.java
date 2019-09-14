package com.matchbook.sdk.rest.dtos.events.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.events.Sport;
import com.matchbook.sdk.rest.dtos.events.SportsResponse;

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
