package com.matchbook.sdk.rest.dtos.offers;

import com.matchbook.sdk.rest.dtos.PageableRequest;
import com.matchbook.sdk.rest.dtos.prices.Side;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class OffersGetRequest extends PageableRequest {

    private final Set<Long> sportIds;
    private final Set<Long> eventIds;
    private final Set<Long> marketIds;
    private final Set<Long> runnersIds;
    private final Set<OfferStatus> statuses;
    private final Side side;
    private final Integer interval;
    private final boolean includeEdits;

    private OffersGetRequest(Init<?> init) {
        super(init);

        this.sportIds = init.sportIds;
        this.eventIds = init.eventIds;
        this.marketIds = init.marketIds;
        this.runnersIds = init.runnersIds;
        this.statuses = init.statuses;
        this.side = init.side;
        this.interval = init.interval;
        this.includeEdits = init.includeEdits;
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
        if (Objects.nonNull(sportIds) && !sportIds.isEmpty()) {
            List<String> ids = sportIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("sport-ids", String.join(",", ids));
        }
        if (Objects.nonNull(eventIds) && !eventIds.isEmpty()) {
            List<String> ids = eventIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("event-ids", String.join(",", ids));
        }
        if (Objects.nonNull(marketIds) && !marketIds.isEmpty()) {
            List<String> ids = marketIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("market-ids", String.join(",", ids));
        }
        if (Objects.nonNull(runnersIds) && !runnersIds.isEmpty()) {
            List<String> ids = runnersIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("runner-ids", String.join(",", ids));
        }
        if (Objects.nonNull(statuses) && !statuses.isEmpty()) {
            List<String> states = statuses.stream()
                    .map(OfferStatus::name)
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

    private static abstract class Init<T extends Init<T>> extends PageableRequest.Init<T> {

        private Set<Long> sportIds;
        private Set<Long> eventIds;
        private Set<Long> marketIds;
        private Set<Long> runnersIds;
        private Set<OfferStatus> statuses;
        private Side side;
        private Integer interval;
        private boolean includeEdits;

        private Init() {
            includeEdits = false;
        }

        public T sportIds(Set<Long> sportIds) {
            this.sportIds = sportIds;
            return self();
        }

        public T eventIds(Set<Long> eventIds) {
            this.eventIds = eventIds;
            return self();
        }

        public T marketIds(Set<Long> marketIds) {
            this.marketIds = marketIds;
            return self();
        }

        public T runnersIds(Set<Long> runnersIds) {
            this.runnersIds = runnersIds;
            return self();
        }

        public T statuses(Set<OfferStatus> statuses) {
            this.statuses = statuses;
            return self();
        }

        public T side(Side side) {
            this.side = side;
            return self();
        }

        public T interval(Integer interval) {
            this.interval = interval;
            return self();
        }

        public T includeEdits(boolean includeEdits) {
            this.includeEdits = includeEdits;
            return self();
        }

        public OffersGetRequest build() {
            return new OffersGetRequest(this);
        }
    }

    public static class Builder extends Init<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
    }

}
