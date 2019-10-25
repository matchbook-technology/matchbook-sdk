package com.matchbook.sdk.rest.dtos.events;

import java.util.HashMap;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.prices.BasePricesRequest;

public class MarketRequest extends BasePricesRequest {

    private final Long eventId;
    private final Long marketId;
    private final boolean includePrices;

    private MarketRequest(Init<?> init) {
        super(init);

        this.eventId = init.eventId;
        this.marketId = init.marketId;
        this.includePrices = init.includePrices;
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public boolean includePrices() {
        return includePrices;
    }

    @Override
    public String resourcePath() {
        return "events/" + eventId +
                "/markets/" + marketId;
    }

    @Override
    public Map<String, String> parameters() {
        Map<String, String> parameters = new HashMap<>();
        if (includePrices) {
            parameters.put("include-prices", "true");
            parameters.putAll(pricesParameters());
        }
        return parameters;
    }

    @Override
    public String toString() {
        return MarketRequest.class.getSimpleName() + " {" +
                "eventId=" + eventId +
                ", marketId=" + marketId +
                ", includePrices=" + includePrices +
                (includePrices ? (
                        ", oddsType=" + oddsType +
                        ", exchangeType=" + exchangeType +
                        ", side=" + side +
                        ", currency=" + currency +
                        ", minimumLiquidity=" + minimumLiquidity +
                        ", priceMode=" + priceMode
                ) : "") +
                "}";
    }

    private static abstract class Init<T extends Init<T>> extends BasePricesRequest.Init<T> {

        private final Long eventId;
        private final Long marketId;
        private boolean includePrices;

        private Init(Long eventId, Long marketId) {
            this.eventId = eventId;
            this.marketId = marketId;
            includePrices = false;
        }

        public T includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return self();
        }

        public MarketRequest build() {
            return new MarketRequest(this);
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
