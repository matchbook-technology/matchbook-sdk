package com.matchbook.sdk.rest.readers.events;

import com.matchbook.sdk.rest.dtos.events.Runner;
import com.matchbook.sdk.rest.dtos.events.RunnersResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReaderTest;

import org.mockito.Mock;

class RunnersReaderTest extends PageableResponseReaderTest<RunnersReader, Runner, RunnersResponse> {

    @Mock
    private RunnerReader runnerReader;

    @Mock
    private Runner runner;

    @Override
    protected RunnersReader newReader() {
        return new RunnersReader(runnerReader);
    }

    @Override
    protected RunnerReader getItemsReader() {
        return runnerReader;
    }

    @Override
    protected Runner getItem() {
        return runner;
    }

    @Override
    protected String getItemsFieldName() {
        return "runners";
    }

}
