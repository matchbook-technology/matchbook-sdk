package com.matchbook.sdk.rest.dtos.events;

import java.util.HashMap;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.prices.AbstractPricesRequest;

public class RunnerRequest extends AbstractPricesRequest {

    private final Long runnerId;
    private final Long eventId;
    private final Long marketId;
    private final boolean includePrices;

    private RunnerRequest(Init<?> init) {
        super(init);

        this.runnerId = init.runnerId;
        this.eventId = init.eventId;
        this.marketId = init.marketId;
        this.includePrices = init.includePrices;
    }

    public Long getRunnerId() {
        return runnerId;
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
                "/markets/" + marketId +
                "/runners/" + runnerId;
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
        return RunnerRequest.class.getSimpleName() + " {" +
                "runnerId=" + runnerId +
                ", eventId=" + eventId +
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

    private static abstract class Init<T extends Init<T>> extends AbstractPricesRequest.Init<T> {

        private final Long eventId;
        private final Long marketId;
        private final Long runnerId;
        private boolean includePrices;

        private Init(Long eventId, Long marketId, Long runnerId) {
            this.eventId = eventId;
            this.marketId = marketId;
            this.runnerId = runnerId;
            includePrices = false;
        }

        public T includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return self();
        }

        public RunnerRequest build() {
            return new RunnerRequest(this);
        }

    }

    public static class Builder extends Init<Builder> {

        public Builder(Long eventId, Long marketId, Long runnerId) {
            super(eventId, marketId, runnerId);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

}
