package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.prices.PageablePricesRequest;
import com.matchbook.sdk.core.clients.rest.dtos.prices.PageablePricesRequestBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RunnersRequest extends PageablePricesRequest {

    private final Long eventId;
    private final Long marketId;
    private final Set<RunnerStatus> statuses;
    private final boolean includeWithdrawn;
    private final boolean includePrices;

    private RunnersRequest(RunnersRequest.Builder builder) {
        super(builder);

        this.eventId = builder.eventId;
        this.marketId = builder.marketId;
        this.statuses = builder.statuses;
        this.includeWithdrawn = builder.includeWithdrawn;
        this.includePrices = builder.includePrices;
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
        if (!statuses.isEmpty()) {
            List<String> states = statuses.stream()
                    .map(Enum::name)
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

    public static class Builder extends PageablePricesRequestBuilder {

        private final Long eventId;
        private final Long marketId;
        private Set<RunnerStatus> statuses;
        private boolean includeWithdrawn;
        private boolean includePrices;

        public Builder(Long eventId, Long marketId) {
            this.eventId = eventId;
            this.marketId = marketId;
            statuses = new HashSet<>();
            includeWithdrawn = true;
            includePrices = false;
        }

        public Builder statuses(Set<RunnerStatus> statuses) {
            this.statuses = statuses;
            return this;
        }

        public Builder includeWithdrawn(boolean includeWithdrawn) {
            this.includeWithdrawn = includeWithdrawn;
            return this;
        }

        public Builder includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return this;
        }

        public RunnersRequest build() {
            return new RunnersRequest(this);
        }
    }

}
