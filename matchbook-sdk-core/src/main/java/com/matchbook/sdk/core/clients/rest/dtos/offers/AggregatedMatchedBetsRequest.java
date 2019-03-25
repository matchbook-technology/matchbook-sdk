package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.matchbook.sdk.core.clients.rest.dtos.PageableRequest;
import com.matchbook.sdk.core.clients.rest.dtos.PageableRequestBuilder;
import com.matchbook.sdk.core.clients.rest.dtos.prices.Side;

public class AggregatedMatchedBetsRequest extends PageableRequest {

    private final Set<Long> eventIds;
    private final Set<Long> marketIds;
    private final Set<Long> runnersIds;
    private final Side side;
    private final AggregationType aggregationType;

    private AggregatedMatchedBetsRequest(AggregatedMatchedBetsRequest.Builder builder) {
        super(builder);

        this.eventIds = builder.eventIds;
        this.marketIds = builder.marketIds;
        this.runnersIds = builder.runnersIds;
        this.side = builder.side;
        this.aggregationType = builder.aggregationType;
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

    public Side getSide() {
        return side;
    }

    public AggregationType getAggregationType() {
        return aggregationType;
    }

    @Override
    public String resourcePath() {
        return "bets/matched/aggregated";
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
        if (Objects.nonNull(side)) {
            parameters.put("side", side.name());
        }
        if (Objects.nonNull(aggregationType)) {
            parameters.put("aggregation-type", aggregationType.name());
        }
        parameters.putAll(pageParameters());
        return parameters;
    }

    @Override
    public String toString() {
        return AggregatedMatchedBetsRequest.class.getSimpleName() + " {" +
                "eventIds=" + eventIds +
                ", marketIds=" + marketIds +
                ", runnersIds=" + runnersIds +
                ", side=" + side +
                ", aggregationType=" + aggregationType +
                ", offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    public static class Builder extends PageableRequestBuilder {

        private Set<Long> eventIds;
        private Set<Long> marketIds;
        private Set<Long> runnersIds;
        private Side side;
        private AggregationType aggregationType;

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

        public Builder side(Side side) {
            this.side = side;
            return this;
        }

        public Builder aggregationType(AggregationType aggregationType) {
            this.aggregationType = aggregationType;
            return this;
        }

        public AggregatedMatchedBetsRequest build() {
            return new AggregatedMatchedBetsRequest(this);
        }
    }

}
