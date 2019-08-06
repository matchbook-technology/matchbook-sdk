package com.matchbook.sdk.rest.dtos.events;

import java.util.HashMap;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.prices.AbstractPricesRequest;

public class MarketRequest extends AbstractPricesRequest {

    private final Long marketId;
    private final Long eventId;
    private final boolean includePrices;

    private MarketRequest(Init<?> init) {
        super(init);

        this.marketId = init.marketId;
        this.eventId = init.eventId;
        this.includePrices = init.includePrices;
    }

    public Long getMarketId() {
        return marketId;
    }

    public Long getEventId() {
        return eventId;
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
                "marketId=" + marketId +
                ", eventId=" + eventId +
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

    private static abstract class Init<T extends Init<T>> extends AbstractPricesRequest.Init<T> {

        private final Long marketId;
        private final Long eventId;
        private boolean includePrices;

        public Init(Long marketId, Long eventId) {
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

        public Builder(Long marketId, Long eventId) {
            super(marketId, eventId);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
