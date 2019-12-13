package com.matchbook.sdk.rest.dtos.events;

import java.util.HashMap;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.prices.BasePricesRequest;

public class RunnerRequest extends BasePricesRequest {

    private final Long eventId;
    private final Long marketId;
    private final Long runnerId;
    private final boolean includePrices;

    private RunnerRequest(Init<?> init) {
        super(init);

        this.eventId = init.eventId;
        this.marketId = init.marketId;
        this.runnerId = init.runnerId;
        this.includePrices = init.includePrices;
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public Long getRunnerId() {
        return runnerId;
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
                "eventId=" + eventId +
                ", marketId=" + marketId +
                ", runnerId=" + runnerId +
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
