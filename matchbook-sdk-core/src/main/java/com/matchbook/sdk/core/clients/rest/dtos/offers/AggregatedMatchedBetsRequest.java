package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.util.Set;

import com.matchbook.sdk.core.clients.rest.dtos.PageableRequest;
import com.matchbook.sdk.core.clients.rest.dtos.PageableRequestBuilder;
import com.matchbook.sdk.core.clients.rest.dtos.prices.Side;

public class AggregatedMatchedBetsRequest extends PageableRequest {

    private final Set<Long> eventIds;
    private final Set<Long> marketIds;
    private final Set<Long> runnersIds;
    private final Side side;
    private final AggregationType aggregationType;

    private AggregatedMatchedBetsRequest(AggregatedMatchedBetsRequest.Builder builder) {
        super(builder);

        this.eventIds = builder.eventIds;
        this.marketIds = builder.marketIds;
        this.runnersIds = builder.runnersIds;
        this.side = builder.side;
        this.aggregationType = builder.aggregationType;
    }

    public Set<Long> getEventIds() {
        return eventIds;
    }

    public Set<Long> getMarketIds() {
        return marketIds;
    }

    public Set<Long> getRunnersIds() {
        return runnersIds;
    }

    public Side getSide() {
        return side;
    }

    public AggregationType getAggregationType() {
        return aggregationType;
    }

    @Override
    public String toString() {
        return AggregatedMatchedBetsRequest.class.getSimpleName() + " {" +
                "eventIds=" + eventIds +
                ", marketIds=" + marketIds +
                ", runnersIds=" + runnersIds +
                ", side=" + side +
                ", aggregationType=" + aggregationType +
                ", offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    public static class Builder extends PageableRequestBuilder {

        private Set<Long> eventIds;
        private Set<Long> marketIds;
        private Set<Long> runnersIds;
        private Side side;
        private AggregationType aggregationType;

        public Builder eventIds(Set<Long> eventIds) {
            this.eventIds = eventIds;
            return this;
        }

        public Builder marketIds(Set<Long> marketIds) {
            this.marketIds = marketIds;
            return this;
        }

        public Builder runnersIds(Set<Long> runnersIds) {
            this.runnersIds = runnersIds;
            return this;
        }

        public Builder side(Side side) {
            this.side = side;
            return this;
        }

        public Builder aggregationType(AggregationType aggregationType) {
            this.aggregationType = aggregationType;
            return this;
        }

        public AggregatedMatchedBetsRequest build() {
            return new AggregatedMatchedBetsRequest(this);
        }
    }

}
