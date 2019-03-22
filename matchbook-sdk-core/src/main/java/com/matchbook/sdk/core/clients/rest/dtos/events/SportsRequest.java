package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.PageableRequest;
import com.matchbook.sdk.core.clients.rest.dtos.PageableRequestBuilder;
import com.matchbook.sdk.core.clients.rest.dtos.prices.PageablePricesRequestBuilder;

public class SportsRequest extends PageableRequest {

    private SportsRequest(SportsRequest.Builder builder) {
        super(builder);
    }

    @Override
    public String toString() {
        return SportsRequest.class.getSimpleName() + " {" +
                "offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    public static class Builder extends PageableRequestBuilder {

        public SportsRequest build() {
            return new SportsRequest(this);
        }
    }

}
