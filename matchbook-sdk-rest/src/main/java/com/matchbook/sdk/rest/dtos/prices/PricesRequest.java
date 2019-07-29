package com.matchbook.sdk.rest.dtos.prices;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PricesRequest extends AbstractPricesRequest {

    private final Long eventId;
    private final Long marketId;
    private final Long runnerId;

    private PricesRequest(Init<?> init) {
        super(init);

        this.eventId = init.eventId;
        this.marketId = init.marketId;
        this.runnerId = init.runnerId;
    }


    protected static abstract class Init<T extends Init<T>> extends AbstractPricesRequest.Init<T> {
        private final Long eventId;
        private final Long marketId;
        private final Long runnerId;

        public Init(Long eventId, Long marketId, Long runnerId) {
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
        Map<String, String> parameters = new HashMap<>();
        if (Objects.nonNull(currency)) {
            parameters.put("currency", currency.name());
        }
        if (Objects.nonNull(exchangeType)) {
            parameters.put("exchange-type", exchangeType.name());
        }
        if (Objects.nonNull(oddsType)) {
            parameters.put("odds-type", oddsType.name());
        }
        if (Objects.nonNull(side)) {
            parameters.put("side", side.name());
        }
        if (Objects.nonNull(minimumLiquidity)) {
            parameters.put("minimum-liquidity", minimumLiquidity.toPlainString());
        }
        if (Objects.nonNull(priceMode)) {
            parameters.put("price-mode", priceMode.name());
        }
        return parameters;
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
}
