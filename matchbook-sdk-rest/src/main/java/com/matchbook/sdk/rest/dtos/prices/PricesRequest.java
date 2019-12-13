package com.matchbook.sdk.rest.dtos.prices;

import java.util.Map;

public class PricesRequest extends BasePricesRequest {

    private final Long eventId;
    private final Long marketId;
    private final Long runnerId;

    private PricesRequest(Init<?> init) {
        super(init);

        this.eventId = init.eventId;
        this.marketId = init.marketId;
        this.runnerId = init.runnerId;
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

    @Override
    public String resourcePath() {
        return "events/" + eventId +
                "/markets/" + marketId +
                "/runners/" + runnerId + "/prices";
    }

    @Override
    public Map<String, String> parameters() {
        return pricesParameters();
    }

    @Override
    public String toString() {
        return PricesRequest.class.getSimpleName() + " {" +
                "eventId=" + eventId +
                ", marketId=" + marketId +
                ", runnerId=" + runnerId +
                ", oddsType=" + oddsType +
                ", exchangeType=" + exchangeType +
                ", side=" + side +
                ", currency=" + currency +
                ", minimumLiquidity=" + minimumLiquidity +
                ", priceMode=" + priceMode +
                "}";
    }

    private static abstract class Init<T extends Init<T>> extends BasePricesRequest.Init<T> {
        private final Long eventId;
        private final Long marketId;
        private final Long runnerId;

        private Init(Long eventId, Long marketId, Long runnerId) {
            this.eventId = eventId;
            this.marketId = marketId;
            this.runnerId = runnerId;
        }

        public PricesRequest build() {
            return new PricesRequest(this);
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
