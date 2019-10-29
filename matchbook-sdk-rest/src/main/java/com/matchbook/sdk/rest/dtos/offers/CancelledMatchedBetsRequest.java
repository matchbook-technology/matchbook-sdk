package com.matchbook.sdk.rest.dtos.offers;

import com.matchbook.sdk.rest.dtos.PageableRequest;

import java.util.HashMap;
import java.util.Map;

public class CancelledMatchedBetsRequest extends PageableRequest {

    private static final MatchedBetStatus DEFAULT_STATUS = MatchedBetStatus.CANCELLED;

    private final MatchedBetStatus status;

    private CancelledMatchedBetsRequest(Init<?> builder) {
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
        Map<String, String> parameters = new HashMap<>();
        parameters.put("status", status.name());
        parameters.putAll(pageParameters());
        return parameters;
    }

    @Override
    public String toString() {
        return CancelledMatchedBetsRequest.class.getSimpleName() + " {" +
                "status=" + status +
                ", offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    private static abstract class Init<T extends Init<T>> extends PageableRequest.Init<T> {

        public CancelledMatchedBetsRequest build() {
            return new CancelledMatchedBetsRequest(this);
        }
    }

    public static class Builder extends Init<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
    }

}
