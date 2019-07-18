package com.matchbook.sdk.core.dtos.events;

import com.matchbook.sdk.core.dtos.PageableResponse;

import java.util.Collection;
import java.util.List;

public class RunnersResponse extends PageableResponse<Runner> {

    private List<Runner> runners;

    public List<Runner> getRunners() {
        return runners;
    }

    public void setRunners(List<Runner> runners) {
        this.runners = runners;
    }

    @Override
    public Collection<Runner> getContent() {
        return runners;
    }

    @Override
    public String toString() {
        return RunnersResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", runners=" + runners +
                "}";
    }

}