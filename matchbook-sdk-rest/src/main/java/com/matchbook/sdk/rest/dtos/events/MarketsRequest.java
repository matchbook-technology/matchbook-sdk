package com.matchbook.sdk.rest.dtos.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.matchbook.sdk.rest.dtos.prices.PageablePricesRequest;

public class MarketsRequest extends PageablePricesRequest {

    private final Long eventId;
    private final Set<MarketType> types;
    private final Set<MarketStatus> statuses;
    private final boolean includePrices;

    private MarketsRequest(Init<?> init) {
        super(init);

        this.eventId = init.eventId;
        this.types = init.types;
        this.statuses = init.statuses;
        this.includePrices = init.includePrices;
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

    protected static abstract class Init<T extends Init<T>> extends PageablePricesRequest.Init<T> {

        private Long eventId;
        private Set<MarketType> types;
        private Set<MarketStatus> statuses;
        private boolean includePrices;

        public Init(Long eventId) {
            this.eventId = eventId;

            types = new HashSet<>();
            statuses = new HashSet<>();
            includePrices = false;
        }

        public T types(Set<MarketType> types) {
            this.types = types;
            return self();
        }

        public T statuses(Set<MarketStatus> statuses) {
            this.statuses = statuses;
            return self();
        }

        public T includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return self();
        }

        public MarketsRequest build() {
            return new MarketsRequest(this);
        }
    }


    public static class Builder extends Init<Builder> {

        public Builder(Long eventId) {
            super(eventId);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
