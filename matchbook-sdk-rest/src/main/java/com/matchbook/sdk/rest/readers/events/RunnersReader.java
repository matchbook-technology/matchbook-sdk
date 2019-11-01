package com.matchbook.sdk.rest.readers.events;

import com.matchbook.sdk.rest.dtos.events.Runner;
import com.matchbook.sdk.rest.dtos.events.RunnersResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReader;

public class RunnersReader extends PageableResponseReader<Runner, RunnersResponse> {

    public RunnersReader() {
        super(new RunnerReader());
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
