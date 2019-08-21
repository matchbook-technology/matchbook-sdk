package com.matchbook.sdk.rest.dtos.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.matchbook.sdk.rest.dtos.prices.PageablePricesRequest;

public class RunnersRequest extends PageablePricesRequest {

    private final Long eventId;
    private final Long marketId;
    private final Set<RunnerStatus> statuses;
    private final boolean includeWithdrawn;
    private final boolean includePrices;

    private RunnersRequest(Init<?> init) {
        super(init);

        this.eventId = init.eventId;
        this.marketId = init.marketId;
        this.statuses = init.statuses;
        this.includeWithdrawn = init.includeWithdrawn;
        this.includePrices = init.includePrices;
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public Set<RunnerStatus> getStatuses() {
        return statuses;
    }

    public boolean isIncludeWithdrawn() {
        return includeWithdrawn;
    }

    public boolean isIncludePrices() {
        return includePrices;
    }

    @Override
    public String resourcePath() {
        return "events/" + eventId +
                "/markets/" + marketId + "/runners";
    }

    @Override
    public Map<String, String> parameters() {
        Map<String, String> parameters = new HashMap<>();
        if (Objects.nonNull(statuses) && !statuses.isEmpty()) {
            List<String> states = statuses.stream()
                    .map(RunnerStatus::name)
                    .collect(Collectors.toList());
            parameters.put("states", String.join(",", states));
        }
        parameters.put("include-withdrawn", String.valueOf(includeWithdrawn));
        if (includePrices) {
            parameters.put("include-prices", "true");
            parameters.putAll(pricesParameters());
        }
        return parameters;
    }

    @Override
    public String toString() {
        return RunnersRequest.class.getSimpleName() + " {" +
                "eventId=" + eventId +
                ", marketId=" + marketId +
                ", statuses=" + statuses +
                ", includeWithdrawn=" + includeWithdrawn +
                ", includePrices=" + includePrices +
                (includePrices ? (
                        ", oddsType=" + oddsType +
                        ", exchangeType=" + exchangeType +
                        ", side=" + side +
                        ", currency=" + currency +
                        ", minimumLiquidity=" + minimumLiquidity +
                        ", priceMode=" + priceMode
                ) : "") +
                ", offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    private static abstract class Init<T extends Init<T>> extends PageablePricesRequest.Init<T> {

        private Long eventId;
        private Long marketId;
        private Set<RunnerStatus> statuses;
        private boolean includeWithdrawn;
        private boolean includePrices;

        private Init(Long eventId, Long marketId) {
            this.eventId = eventId;
            this.marketId = marketId;

            includeWithdrawn = true;
            includePrices = false;
        }

        public T statuses(Set<RunnerStatus> statuses) {
            this.statuses = statuses;
            return self();
        }

        public T includeWithdrawn(boolean includeWithdrawn) {
            this.includeWithdrawn = includeWithdrawn;
            return self();
        }

        public T includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return self();
        }

        public RunnersRequest build() {
            return new RunnersRequest(this);
        }
    }

    public static class Builder extends Init<Builder> {

        public Builder(Long eventId, Long marketId) {
            super(eventId, marketId);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

}
