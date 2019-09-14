package com.matchbook.sdk.rest.dtos.events.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.events.Runner;
import com.matchbook.sdk.rest.dtos.events.RunnersResponse;

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
