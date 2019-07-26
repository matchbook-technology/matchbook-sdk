package com.matchbook.sdk.rest.dtos.offers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.matchbook.sdk.rest.dtos.PageableRequest;
import com.matchbook.sdk.rest.dtos.PageableRequestBuilder;

public class PositionsRequest extends PageableRequest {

    private final Set<Long> eventIds;
    private final Set<Long> marketIds;
    private final Set<Long> runnersIds;

    private PositionsRequest(PositionsRequest.Builder builder) {
        super(builder);

        this.eventIds = builder.eventIds;
        this.marketIds = builder.marketIds;
        this.runnersIds = builder.runnersIds;
    }

    public Set<Long> getEventIds() {
        return eventIds;
    }

    public Set<Long> getMarketIds() {
        return marketIds;
    }

    public Set<Long> getRunnersIds() {
        return runnersIds;
    }

    @Override
    public String resourcePath() {
        return "account/positions";
    }

    @Override
    public Map<String, String> parameters() {
        Map<String, String> parameters = new HashMap<>();
        if (!eventIds.isEmpty()) {
            List<String> ids = eventIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("event-ids", String.join(",", ids));
        }
        if (!marketIds.isEmpty()) {
            List<String> ids = marketIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("market-ids", String.join(",", ids));
        }
        if (!runnersIds.isEmpty()) {
            List<String> ids = runnersIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("runner-ids", String.join(",", ids));
        }
        parameters.putAll(pageParameters());
        return parameters;
    }

    @Override
    public String toString() {
        return PositionsRequest.class.getSimpleName() + " {" +
                "eventIds=" + eventIds +
                ", marketIds=" + marketIds +
                ", runnersIds=" + runnersIds +
                ", offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    public static class Builder extends PageableRequestBuilder {

        private Set<Long> eventIds;
        private Set<Long> marketIds;
        private Set<Long> runnersIds;

        public Builder eventIds(Set<Long> eventIds) {
            this.eventIds = eventIds;
            return this;
        }

        public Builder marketIds(Set<Long> marketIds) {
            this.marketIds = marketIds;
            return this;
        }

        public Builder runnersIds(Set<Long> runnersIds) {
            this.runnersIds = runnersIds;
            return this;
        }

        public PositionsRequest build() {
            return new PositionsRequest(this);
        }
    }

}
