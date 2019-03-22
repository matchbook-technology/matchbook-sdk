package com.matchbook.sdk.core.clients.rest.dtos.events;

import java.util.Collections;
import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class RunnerResponse implements RestResponse<Runner> {

    private Runner runner;

    public Runner getRunner() {
        return runner;
    }

    @Override
    public List<Runner> getContent() {
        return Collections.singletonList(runner);
    }

    @Override
    public String toString() {
        return RunnerResponse.class.getSimpleName() + " {" +
                "runner=" + runner +
                "}";
    }

}
