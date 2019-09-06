package com.matchbook.sdk.rest.dtos.events.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.events.Runner;
import com.matchbook.sdk.rest.dtos.events.RunnersResponse;

public class RunnersResponseReader extends PageableResponseReader<Runner, RunnersResponse> {

    public RunnersResponseReader() {
        super(new RunnerResponseReader());
    }

    @Override
    protected RunnersResponse newPageableResponse() {
        return new RunnersResponse();
    }

    @Override
    protected String itemsFieldName() {
        return "runners";
    }

}
