package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookResponse;

public class RunnerResponse implements MatchbookResponse {

    private Runner runner;

    public Runner getRunner() {
        return runner;
    }

    @Override
    public String toString() {
        return RunnerResponse.class.getSimpleName() + " {" +
                "runner=" + runner +
                "}";
    }

}
