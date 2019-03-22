package com.matchbook.sdk.core.clients.rest.dtos.events;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.matchbook.sdk.core.clients.rest.dtos.prices.PageablePricesRequest;
import com.matchbook.sdk.core.clients.rest.dtos.prices.PageablePricesRequestBuilder;

public class MarketsRequest extends PageablePricesRequest {

    private final Long eventId;
    private final Set<MarketType> types;
    private final Set<MarketStatus> statuses;
    private final boolean includePrices;

    private MarketsRequest(MarketsRequest.Builder builder) {
        super(builder);

        this.eventId = builder.eventId;
        this.types = builder.types;
        this.statuses = builder.statuses;
        this.includePrices = builder.includePrices;
    }

    public Long getEventId() {
        return eventId;
    }

    public Set<MarketType> getTypes() {
        return types;
    }

    public Set<MarketStatus> getStatuses() {
        return statuses;
    }

    public boolean includePrices() {
        return includePrices;
    }

    @Override
    public String resourcePath() {
        return "events/" + eventId + "/markets";
    }

    @Override
    public Map<String, String> parameters() {
        Map<String, String> parameters = new HashMap<>();
        if (!types.isEmpty()) {
            List<String> marketTypes = types.stream()
                    .map(Enum::name)
                    .collect(Collectors.toList());
            parameters.put("types", String.join(",", marketTypes));
        }
        if (!statuses.isEmpty()) {
            List<String> states = statuses.stream()
                    .map(Enum::name)
                    .collect(Collectors.toList());
            parameters.put("states", String.join(",", states));
        }
        if (includePrices) {
            parameters.put("include-prices", "true");
            parameters.putAll(pricesParameters());
        }
        return parameters;
    }

    @Override
    public String toString() {
        return MarketsRequest.class.getSimpleName() + " {" +
                "eventId=" + eventId +
                ", types=" + types +
                ", statuses=" + statuses +
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
        private Set<MarketType> types;
        private Set<MarketStatus> statuses;
        private boolean includePrices;

        public Builder(Long eventId) {
            this.eventId = eventId;
            includePrices = false;
        }

        public Builder types(Set<MarketType> types) {
            this.types = types;
            return this;
        }

        public Builder statuses(Set<MarketStatus> statuses) {
            this.statuses = statuses;
            return this;
        }

        public Builder includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return this;
        }

        public MarketsRequest build() {
            return new MarketsRequest(this);
        }
    }

}
