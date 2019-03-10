package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookPageableResponse;

public class SportsResponse extends MatchbookPageableResponse<Sport> {

    @Override
    public String toString() {
        return SportsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", sports=" + content +
                "}";
    }

}
