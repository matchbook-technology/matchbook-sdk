package com.matchbook.sdk.rest.dtos.offers;

import com.matchbook.sdk.rest.dtos.PageableResponse;

public class PositionsResponse extends PageableResponse<Position> {

    @Override
    public String toString() {
        return PositionsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", positions=" + items +
                "}";
    }

}
