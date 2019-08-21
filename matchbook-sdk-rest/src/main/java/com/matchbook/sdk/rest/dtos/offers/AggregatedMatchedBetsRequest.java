package com.matchbook.sdk.rest.dtos.offers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.matchbook.sdk.rest.dtos.PageableRequest;
import com.matchbook.sdk.rest.dtos.prices.Side;

public class AggregatedMatchedBetsRequest extends PageableRequest {

    private final Set<Long> eventIds;
    private final Set<Long> marketIds;
    private final Set<Long> runnersIds;
    private final Side side;
    private final AggregationType aggregationType;

    public AggregatedMatchedBetsRequest(Init<?> init) {
        super(init);

        this.eventIds = init.eventIds;
        this.marketIds = init.marketIds;
        this.runnersIds = init.runnersIds;
        this.side = init.side;
        this.aggregationType = init.aggregationType;
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
        if (Objects.nonNull(eventIds) && !eventIds.isEmpty()) {
            List<String> ids = eventIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("event-ids", String.join(",", ids));
        }
        if (Objects.nonNull(marketIds) && !marketIds.isEmpty()) {
            List<String> ids = marketIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("market-ids", String.join(",", ids));
        }
        if (Objects.nonNull(runnersIds) && !runnersIds.isEmpty()) {
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

    private static abstract class Init<T extends Init<T>> extends PageableRequest.Init<T> {

        private Set<Long> eventIds;
        private Set<Long> marketIds;
        private Set<Long> runnersIds;
        private Side side;
        private AggregationType aggregationType;

        public T eventIds(Set<Long> eventIds) {
            this.eventIds = eventIds;
            return self();
        }

        public T marketIds(Set<Long> marketIds) {
            this.marketIds = marketIds;
            return self();
        }

        public T runnersIds(Set<Long> runnersIds) {
            this.runnersIds = runnersIds;
            return self();
        }

        public T side(Side side) {
            this.side = side;
            return self();
        }

        public T aggregationType(AggregationType aggregationType) {
            this.aggregationType = aggregationType;
            return self();
        }

        public AggregatedMatchedBetsRequest build() {
            return new AggregatedMatchedBetsRequest(this);
        }
    }

    public static class Builder extends Init<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
    }

}
