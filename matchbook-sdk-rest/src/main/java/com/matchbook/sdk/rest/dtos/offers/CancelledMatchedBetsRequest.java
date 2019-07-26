package com.matchbook.sdk.rest.dtos.offers;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.PageableRequest;
import com.matchbook.sdk.rest.dtos.PageableRequestBuilder;

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
    public String resourcePath() {
        return "bets";
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.singletonMap("status", status.name());
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
