package com.matchbook.sdk.rest.dtos.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.matchbook.sdk.rest.dtos.PageableResponse;

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
    public void setContent(Collection<Runner> runners) {
        this.runners = new ArrayList<>(runners);
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
