package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.matchbook.sdk.core.clients.rest.dtos.RestRequest;

public class CancelOffersRequest implements RestRequest {

    private final Set<Long> eventIds;
    private final Set<Long> marketIds;
    private final Set<Long> runnersIds;
    private final Set<Long> offerIds;

    private CancelOffersRequest(CancelOffersRequest.Builder builder) {
        this.eventIds = builder.eventIds;
        this.marketIds = builder.marketIds;
        this.runnersIds = builder.runnersIds;
        this.offerIds = builder.offerIds;
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

    public Set<Long> getOfferIds() {
        return offerIds;
    }

    @Override
    public String resourcePath() {
        return "v2/offers";
    }

    @Override
    public Map<String, String> parameters() {
        Map<String, String> parameters = new HashMap<>();
        if (!eventIds.isEmpty()) {
            List<String> ids = eventIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("event-ids", String.join(",", ids));
        }
        if (!marketIds.isEmpty()) {
            List<String> ids = marketIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("market-ids", String.join(",", ids));
        }
        if (!runnersIds.isEmpty()) {
            List<String> ids = runnersIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("runner-ids", String.join(",", ids));
        }
        if (!offerIds.isEmpty()) {
            List<String> ids = offerIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("offer-ids", String.join(",", ids));
        }
        return parameters;
    }

    @Override
    public String toString() {
        return CancelOffersRequest.class.getSimpleName() + " {" +
                "eventIds=" + eventIds +
                ", marketIds=" + marketIds +
                ", runnersIds=" + runnersIds +
                ", offerIds=" + offerIds +
                "}";
    }

    public static class Builder {

        private Set<Long> eventIds;
        private Set<Long> marketIds;
        private Set<Long> runnersIds;
        private Set<Long> offerIds;

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

        public Builder offerIds(Set<Long> offerIds) {
            this.offerIds = offerIds;
            return this;
        }

        public CancelOffersRequest build() {
            return new CancelOffersRequest(this);
        }
    }

}
