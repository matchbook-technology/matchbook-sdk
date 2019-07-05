package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.matchbook.sdk.core.clients.rest.dtos.PageableRequest;
import com.matchbook.sdk.core.clients.rest.dtos.PageableRequestBuilder;
import com.matchbook.sdk.core.clients.rest.dtos.prices.Side;

public class OffersGetRequest extends PageableRequest {

    private final Set<Long> sportIds;
    private final Set<Long> eventIds;
    private final Set<Long> marketIds;
    private final Set<Long> runnersIds;
    private final Set<OfferStatus> statuses;
    private final Side side;
    private final Integer interval;
    private final boolean includeEdits;

    private OffersGetRequest(OffersGetRequest.Builder builder) {
        super(builder);

        this.sportIds = builder.sportIds;
        this.eventIds = builder.eventIds;
        this.marketIds = builder.marketIds;
        this.runnersIds = builder.runnersIds;
        this.statuses = builder.statuses;
        this.side = builder.side;
        this.interval = builder.interval;
        this.includeEdits = builder.includeEdits;
    }

    public Set<Long> getSportIds() {
        return sportIds;
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

    public Set<OfferStatus> getStatuses() {
        return statuses;
    }

    public Side getSide() {
        return side;
    }

    public Integer getInterval() {
        return interval;
    }

    public boolean includeEdits() {
        return includeEdits;
    }

    @Override
    public String resourcePath() {
        return "v2/offers";
    }

    @Override
    public Map<String, String> parameters() {
        Map<String, String> parameters = new HashMap<>();
        if (!sportIds.isEmpty()) {
            List<String> ids = sportIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("sport-ids", String.join(",", ids));
        }
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
        if (!statuses.isEmpty()) {
            List<String> states = statuses.stream()
                    .map(Enum::name)
                    .collect(Collectors.toList());
            parameters.put("status", String.join(",", states));
        }
        if (Objects.nonNull(side)) {
            parameters.put("side", side.name());
        }
        if (Objects.nonNull(interval)) {
            parameters.put("interval", String.valueOf(interval));
        }
        parameters.put("include-edits", String.valueOf(includeEdits));
        parameters.putAll(pageParameters());
        return parameters;
    }

    @Override
    public String toString() {
        return OffersGetRequest.class.getSimpleName() + " {" +
                "sportIds=" + sportIds +
                ", eventIds=" + eventIds +
                ", marketIds=" + marketIds +
                ", runnersIds=" + runnersIds +
                ", statuses=" + statuses +
                ", side=" + side +
                ", interval=" + interval +
                ", includeEdits=" + includeEdits +
                ", offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    public static class Builder extends PageableRequestBuilder {

        private Set<Long> sportIds;
        private Set<Long> eventIds;
        private Set<Long> marketIds;
        private Set<Long> runnersIds;
        private Set<OfferStatus> statuses;
        private Side side;
        private Integer interval;
        private boolean includeEdits;

        public Builder() {
            includeEdits = false;
        }

        public Builder sportIds(Set<Long> sportIds) {
            this.sportIds = sportIds;
            return this;
        }

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

        public Builder statuses(Set<OfferStatus> statuses) {
            this.statuses = statuses;
            return this;
        }

        public Builder side(Side side) {
            this.side = side;
            return this;
        }

        public Builder interval(Integer interval) {
            this.interval = interval;
            return this;
        }

        public Builder includeEdits(boolean includeEdits) {
            this.includeEdits = includeEdits;
            return this;
        }

        public OffersGetRequest build() {
            return new OffersGetRequest(this);
        }
    }

}
