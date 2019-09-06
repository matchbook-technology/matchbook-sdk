package com.matchbook.sdk.rest.dtos.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.matchbook.sdk.rest.dtos.PageableResponse;

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
    public void setContent(Collection<Sport> sports) {
        this.sports = new ArrayList<>(sports);
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
