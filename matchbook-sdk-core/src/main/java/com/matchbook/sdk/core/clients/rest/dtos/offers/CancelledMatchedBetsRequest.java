/*
 * Copyright (c) 2019 Triplebet Limited. All right reserved. Inchalla, Le Val, Alderney, GY9 3UL.
 * Company Registration Number: 1827.
 */

package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.PageableRequest;
import com.matchbook.sdk.core.clients.rest.dtos.PageableRequestBuilder;

public class CancelledMatchedBetsRequest extends PageableRequest {

    private static final MatchedBetStatus DEFAULT_STATUS = MatchedBetStatus.CANCELLED;

    private final MatchedBetStatus status;

    private CancelledMatchedBetsRequest(CancelledMatchedBetsRequest.Builder builder) {
        super(builder);

        this.status = DEFAULT_STATUS;
    }

    public MatchedBetStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return CancelledMatchedBetsRequest.class.getSimpleName() + " {" +
                "status=" + status +
                ", offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    public static class Builder extends PageableRequestBuilder {

        public CancelledMatchedBetsRequest build() {
            return new CancelledMatchedBetsRequest(this);
        }
    }

}
