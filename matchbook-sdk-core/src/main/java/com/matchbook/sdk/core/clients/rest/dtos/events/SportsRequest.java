package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookPageableRequest;
import com.matchbook.sdk.core.clients.rest.dtos.prices.PageablePricesRequestBuilder;

public class SportsRequest extends MatchbookPageableRequest {

    private SportsRequest(SportsRequest.Builder builder) {
        super(builder);
    }

    public static class Builder extends PageablePricesRequestBuilder {

        public SportsRequest build() {
            return new SportsRequest(this);
        }
    }

}
