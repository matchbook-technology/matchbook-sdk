package com.matchbook.sdk.core.dtos.offers;

import java.util.Collection;
import java.util.List;

import com.matchbook.sdk.core.dtos.PageableResponse;

public class PositionsResponse extends PageableResponse<Position> {

    private List<Position> positions;

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public Collection<Position> getContent() {
        return positions;
    }

    @Override
    public String toString() {
        return PositionsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", positions=" + positions +
                "}";
    }

}
