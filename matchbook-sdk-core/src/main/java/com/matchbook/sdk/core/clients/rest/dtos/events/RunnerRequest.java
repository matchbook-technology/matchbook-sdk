package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.prices.BasePricesRequest;
import com.matchbook.sdk.core.clients.rest.dtos.prices.BasePricesRequestBuilder;

public class RunnerRequest extends BasePricesRequest {

    private final Long runnerId;
    private final Long eventId;
    private final Long marketId;
    private final boolean includePrices;

    private RunnerRequest(RunnerRequest.Builder builder) {
        super(builder);

        this.runnerId = builder.runnerId;
        this.eventId = builder.eventId;
        this.marketId = builder.marketId;
        this.includePrices = builder.includePrices;
    }

    public Long getId() {
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

    public static class Builder extends BasePricesRequestBuilder {

        private final Long eventId;
        private final Long marketId;
        private final Long runnerId;
        private boolean includePrices;

        public Builder(Long eventId, Long marketId, Long runnerId) {
            this.eventId = eventId;
            this.marketId = marketId;
            this.runnerId = runnerId;
            includePrices = false;
        }

        public Builder includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return this;
        }

        public RunnerRequest build() {
            return new RunnerRequest(this);
        }
    }

}
