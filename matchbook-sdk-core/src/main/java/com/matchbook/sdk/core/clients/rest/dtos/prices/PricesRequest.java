package com.matchbook.sdk.core.clients.rest.dtos.prices;

public class PricesRequest extends BasePricesRequest {

    private final Long eventId;
    private final Long marketId;
    private final Long runnerId;

    private PricesRequest(PricesRequest.Builder builder) {
        super(builder);

        this.eventId = builder.eventId;
        this.marketId = builder.marketId;
        this.runnerId = builder.runnerId;
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

    public static class Builder extends BasePricesRequestBuilder {

        private final Long eventId;
        private final Long marketId;
        private final Long runnerId;

        public Builder(Long eventId, Long marketId, Long runnerId) {
            this.eventId = eventId;
            this.marketId = marketId;
            this.runnerId = runnerId;
        }

        public PricesRequest build() {
            return new PricesRequest(this);
        }
    }

}
