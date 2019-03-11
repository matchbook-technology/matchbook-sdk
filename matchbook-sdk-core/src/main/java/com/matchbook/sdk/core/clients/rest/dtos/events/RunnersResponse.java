package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookPageableResponse;

public class RunnersResponse extends MatchbookPageableResponse<Runner> {

    @Override
    public String toString() {
        return RunnersResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", runners=" + content +
                "}";
    }

}
