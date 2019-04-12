package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.PageableResponse;

import java.util.Collection;
import java.util.List;

public class SportsResponse extends PageableResponse<Sport> {

    private List<Sport> sports;

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    @Override
    public Collection<Sport> getContent() {
        return sports;
    }

    @Override
    public String toString() {
        return SportsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", sports=" + sports +
                "}";
    }

}
