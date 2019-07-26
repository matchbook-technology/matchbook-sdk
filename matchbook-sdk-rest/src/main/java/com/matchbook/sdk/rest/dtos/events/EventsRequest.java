package com.matchbook.sdk.rest.dtos.events;

import com.matchbook.sdk.rest.dtos.prices.PageablePricesRequest;
import com.matchbook.sdk.rest.dtos.prices.PageablePricesRequestBuilder;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class EventsRequest extends PageablePricesRequest {

    private final Instant after;
    private final Instant before;
    private final Set<Long> sportIds;
    private final Set<Long> categoryIds;
    private final Set<Long> eventIds;
    private final Set<EventStatus> statuses;
    private final boolean includeEventParticipants;
    private final boolean includePrices;

    private EventsRequest(EventsRequest.Builder builder) {
        super(builder);

        this.after = builder.after;
        this.before = builder.before;
        this.sportIds = builder.sportIds;
        this.categoryIds = builder.categoryIds;
        this.eventIds = builder.eventIds;
        this.statuses = builder.statuses;
        this.includeEventParticipants = builder.includeEventParticipants;
        this.includePrices = builder.includePrices;
    }

    public Instant getAfter() {
        return after;
    }

    public Instant getBefore() {
        return before;
    }

    public Set<Long> getSportIds() {
        return sportIds;
    }

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public Set<Long> getEventIds() {
        return eventIds;
    }

    public Set<EventStatus> getStatuses() {
        return statuses;
    }

    public boolean includeEventParticipants() {
        return includeEventParticipants;
    }

    public boolean includePrices() {
        return includePrices;
    }

    @Override
    public String resourcePath() {
        return "events";
    }

    @Override
    public Map<String, String> parameters() {
        Map<String, String> parameters = new HashMap<>();
        if (Objects.nonNull(after)) {
            parameters.put("after", after.toString());
        }
        if (Objects.nonNull(before)) {
            parameters.put("before", before.toString());
        }
        if (!eventIds.isEmpty()) {
            List<String> ids = eventIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("ids", String.join(",", ids));
        }
        if (!categoryIds.isEmpty()) {
            List<String> ids = categoryIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("category-ids", String.join(",", ids));
        }
        if (!sportIds.isEmpty()) {
            List<String> ids = sportIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("sport-ids", String.join(",", ids));
        }
        if (!statuses.isEmpty()) {
            List<String> states = statuses.stream()
                    .map(Enum::name)
                    .collect(Collectors.toList());
            parameters.put("states", String.join(",", states));
        }
        parameters.put("include-event-participants", String.valueOf(includeEventParticipants));
        if (includePrices) {
            parameters.put("include-prices", "true");
            parameters.putAll(pricesParameters());
        }
        return parameters;
    }

    @Override
    public String toString() {
        return EventsRequest.class.getSimpleName() + " {" +
                "after=" + after +
                ", before=" + before +
                ", sportIds=" + sportIds +
                ", categoryIds=" + categoryIds +
                ", eventIds=" + eventIds +
                ", statuses=" + statuses +
                ", includeEventParticipants=" + includeEventParticipants +
                ", includePrices=" + includePrices +
                (includePrices ? (
                    ", oddsType=" + oddsType +
                    ", exchangeType=" + exchangeType +
                    ", side=" + side +
                    ", currency=" + currency +
                    ", minimumLiquidity=" + minimumLiquidity +
                    ", priceMode=" + priceMode
                ) : "") +
                ", offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    public static class Builder extends PageablePricesRequestBuilder {

        private Instant after;
        private Instant before;
        private Set<Long> sportIds;
        private Set<Long> categoryIds;
        private Set<Long> eventIds;
        private Set<EventStatus> statuses;
        private boolean includeEventParticipants;
        private boolean includePrices;

        public Builder() {
            includeEventParticipants = false;
            includePrices = false;
            sportIds = new HashSet<>();
            categoryIds = new HashSet<>();
            eventIds = new HashSet<>();
            statuses = new HashSet<>();
        }

        public Builder after(Instant after) {
            this.after = after;
            return this;
        }

        public Builder before(Instant before) {
            this.before = before;
            return this;
        }

        public Builder sportIds(Set<Long> sportIds) {
            this.sportIds = sportIds;
            return this;
        }

        public Builder categoryIds(Set<Long> categoryIds) {
            this.categoryIds = categoryIds;
            return this;
        }

        public Builder eventIds(Set<Long> eventIds) {
            this.eventIds = eventIds;
            return this;
        }

        public Builder statuses(Set<EventStatus> statuses) {
            this.statuses = statuses;
            return this;
        }

        public Builder includeEventParticipants(boolean includeEventParticipants) {
            this.includeEventParticipants = includeEventParticipants;
            return this;
        }

        public Builder includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return this;
        }

        public EventsRequest build() {
            return new EventsRequest(this);
        }
    }

}
